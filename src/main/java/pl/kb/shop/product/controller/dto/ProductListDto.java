package pl.kb.shop.product.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ProductListDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String currency;
    private String img;
    private String slug;
}
