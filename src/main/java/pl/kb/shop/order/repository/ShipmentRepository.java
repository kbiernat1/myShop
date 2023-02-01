package pl.kb.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kb.shop.order.model.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
