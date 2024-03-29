package pl.kb.shop.order.model.dto;

import lombok.Builder;
import lombok.Getter;
import pl.kb.shop.order.model.Payment;
import pl.kb.shop.order.model.Shipment;

import java.util.List;

@Getter
@Builder
public class InitOrder {
    private List<Shipment> shipments;
    private List<Payment> payments;
}
