package pl.kb.shop.category.model;

import org.springframework.data.domain.Page;
import pl.kb.shop.product.model.Product;

public record CategoryProductsDto(Category category, Page<Product> products) {
}
