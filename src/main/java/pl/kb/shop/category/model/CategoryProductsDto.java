package pl.kb.shop.category.model;

import org.springframework.data.domain.Page;
import pl.kb.shop.product.controller.dto.ProductListDto;

public record CategoryProductsDto(Category category, Page<ProductListDto> products) {
}
