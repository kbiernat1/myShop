package pl.kb.shop.category.dto;

import org.springframework.data.domain.Page;
import pl.kb.shop.common.dto.ProductListDto;
import pl.kb.shop.common.model.Category;

public record CategoryProductsDto(Category category, Page<ProductListDto> products) {
}
