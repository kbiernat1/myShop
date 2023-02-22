package pl.kb.shop.admin.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kb.shop.admin.order.model.AdminOrder;

public interface AdminOrderRepository extends JpaRepository<AdminOrder, Long> {
}
