package pl.kb.shop.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kb.shop.admin.model.AdminProduct;

public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {
}
