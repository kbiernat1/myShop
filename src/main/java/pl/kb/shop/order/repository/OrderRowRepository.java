package pl.kb.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kb.shop.order.model.OrderRow;

public interface OrderRowRepository extends JpaRepository<OrderRow, Long> {
}
