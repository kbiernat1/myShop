package pl.kb.shop.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kb.shop.common.email.EmailClientService;
import pl.kb.shop.common.model.Cart;
import pl.kb.shop.common.model.CartItem;
import pl.kb.shop.common.repository.CartItemRepository;
import pl.kb.shop.common.repository.CartRepository;
import pl.kb.shop.order.model.*;
import pl.kb.shop.order.model.dto.OrderDto;
import pl.kb.shop.order.model.dto.OrderSummary;
import pl.kb.shop.order.repository.OrderRepository;
import pl.kb.shop.order.repository.OrderRowRepository;
import pl.kb.shop.order.repository.PaymentRepository;
import pl.kb.shop.order.repository.ShipmentRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartItemRepository cartItemRepository;
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;
    private final EmailClientService emailClientService;

    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto) {
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.getShipmentId()).orElseThrow();
        Payment payment = paymentRepository.findById(orderDto.getPaymentId()).orElseThrow();
        Order order = Order.builder()
                .first_name(orderDto.getFirstname())
                .last_name(orderDto.getLastname())
                .street(orderDto.getStreet())
                .zipcode(orderDto.getZipcode())
                .city(orderDto.getCity())
                .email(orderDto.getEmail())
                .phone(orderDto.getPhone())
                .placeDate(LocalDateTime.now())
                .orderStatus(OrderStatus.NEW)
                .grossValue(calculatedGrossValue(cart.getItems(), shipment))
                .payment(payment)
                .build();
        Order newOrder = orderRepository.save(order);
        saveOrderRows(cart, newOrder.getId(), shipment);

        cartRepository.deleteCartById(order.getId());
        cartItemRepository.deleteByCartId(orderDto.getCartId());
        emailClientService.getInstance().send(order.getEmail(), "Twoje zamówienie zostało przyjęte", createEmailMsg(order));
        return OrderSummary.builder()
                .id(newOrder.getId())
                .placeDate(newOrder.getPlaceDate())
                .status(newOrder.getOrderStatus())
                .grossValue(newOrder.getGrossValue())
                .payment(payment)
                .build();
    }

    private String createEmailMsg(Order order) {
        return "Dziękujemy za zakupy w naszym serwisie.\n" +
                "Twoje zamówienie zostało przekazane do realizacji. Informujemy, że przygotowanie i wysyłka " +
                "towarów trwa około 3 - 4 dni roboczych (z wyłączeniem weekendów i świąt).\n\n" +
                "DANE ZAMÓWIENIA:\n" +
                "Numer zamówienia: " + order.getId() + "\n" +
                "Data zamówienia: " + order.getPlaceDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "\n" +
                "Kwota łączna: " + order.getGrossValue() + " PLN\n" +
                "Sposób płatności: " + order.getPayment().getName() +
                (order.getPayment().getNote() != null ? "\n" + order.getPayment().getNote() : "") +
                "\n\nADRES DOSTAWY:\n" +
                order.getFirst_name() + " " + order.getLast_name() + "\n" +
                order.getStreet() + "\n" +
                order.getZipcode() + " " + order.getCity();
    }

    private BigDecimal calculatedGrossValue(List<CartItem> items, Shipment shipment) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .add(shipment.getPrice());
    }

    private void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductRows(cart, orderId);
        saveShipmentRow(orderId, shipment);
    }

    private void saveShipmentRow(Long orderId, Shipment shipment) {
        orderRowRepository.save(OrderRow.builder()
                .quantity(1)
                .price(shipment.getPrice())
                .shipmentId(shipment.getId())
                .orderId(orderId)
                .build());
    }

    private void saveProductRows(Cart cart, Long orderId) {
        cart.getItems().stream()
                .map(cartItem -> OrderRow.builder()
                        .quantity(cartItem.getQuantity())
                        .productId(cartItem.getProduct().getId())
                        .price(cartItem.getProduct().getPrice())
                        .orderId(orderId)
                        .build()
                )
                .peek(orderRowRepository::save)
                .toList();
    }
}
