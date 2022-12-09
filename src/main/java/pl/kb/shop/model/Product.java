package pl.kb.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Product {
    private String name;
    private String category;
    private String description;
    private String img;
    private BigDecimal price;
    private String currency;
}
