package pl.kb.shop.admin.controller.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AdminProductDto {
    private String name;
    private String category;
    private String description;
    private String img;
    private BigDecimal price;
    private String currency;
}
