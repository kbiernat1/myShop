package pl.kb.shop.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kb.shop.common.email.EmailClientService;
import pl.kb.shop.common.model.Cart;
import pl.kb.shop.common.repository.CartItemRepository;
import pl.kb.shop.common.repository.CartRepository;
import pl.kb.shop.order.model.*;
import pl.kb.shop.order.model.dto.OrderDto;
import pl.kb.shop.order.model.dto.OrderSummary;
import pl.kb.shop.order.repository.OrderRepository;
import pl.kb.shop.order.repository.OrderRowRepository;
import pl.kb.shop.order.repository.PaymentRepository;
import pl.kb.shop.order.repository.ShipmentRepository;

import static pl.kb.shop.order.service.mapper.OrderEmailMsgMapper.createEmailMsg;
import static pl.kb.shop.order.service.mapper.OrderMapper.*;

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
    public OrderSummary placeOrder(OrderDto orderDto, Long userId) {
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.getShipmentId()).orElseThrow();
        Payment payment = paymentRepository.findById(orderDto.getPaymentId()).orElseThrow();
        Order newOrder = orderRepository.save(createNewOrder(orderDto, cart, shipment, payment, userId));
        saveOrderRows(cart, newOrder.getId(), shipment);

        clearOrderCart(orderDto);
        sendEmailConfirmation(newOrder);
        return createOrderSummary(payment, newOrder);
    }

    private void sendEmailConfirmation(Order newOrder) {
        emailClientService.getInstance()
                .send(newOrder.getEmail(),
                        "Twoje zamówienie zostało przyjęte",
                        createEmailMsg(newOrder));
    }

    private void clearOrderCart(OrderDto orderDto) {
        cartItemRepository.deleteByCartId(orderDto.getCartId());
        cartRepository.deleteCartById(orderDto.getCartId());
    }


    private void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductRows(cart, orderId);
        saveShipmentRow(orderId, shipment);
    }

    private void saveShipmentRow(Long orderId, Shipment shipment) {
        orderRowRepository.save(mapToOrderRow(orderId, shipment));
    }

    private void saveProductRows(Cart cart, Long orderId) {
        cart.getItems().stream()
                .map(cartItem -> mapToOrderRowWithQty(orderId, cartItem)
                )
                .peek(orderRowRepository::save)
                .toList();
    }
}
