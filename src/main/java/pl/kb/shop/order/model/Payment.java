package pl.kb.shop.order.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private PaymentType type;
    private boolean defaultPayment;
    private String note;
}
