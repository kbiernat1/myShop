package pl.kb.shop.admin.order.model;

import lombok.Getter;
import pl.kb.shop.admin.product.model.AdminProduct;
import pl.kb.shop.order.model.Shipment;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_row")
@Getter
public class AdminOrderRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    @OneToOne
    @JoinColumn(name = "productId")
    private AdminProduct product;
    private int quantity;
    private BigDecimal price;
    @OneToOne
    @JoinColumn(name = "shipmentId")
    private Shipment shipment;
}
