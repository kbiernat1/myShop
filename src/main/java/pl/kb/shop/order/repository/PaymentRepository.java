package pl.kb.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kb.shop.order.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
