package pl.kb.shop.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.kb.shop.order.model.dto.InitOrder;
import pl.kb.shop.order.model.dto.OrderDto;
import pl.kb.shop.order.model.dto.OrderSummary;
import pl.kb.shop.order.service.OrderService;
import pl.kb.shop.order.service.ShipmentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ShipmentService shipmentService;

    @PostMapping
    public OrderSummary placeOrder(@RequestBody OrderDto orderDto) {
        return orderService.placeOrder(orderDto);
    }

    @GetMapping("/initData")
    public InitOrder initData() {
        return InitOrder.builder()
                .shipments(shipmentService.getShipments())
                .build();
    }
}
