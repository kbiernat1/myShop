package pl.kb.shop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kb.shop.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
