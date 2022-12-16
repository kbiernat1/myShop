package pl.kb.shop.admin.controller.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import pl.kb.shop.admin.model.AdminProductCurrency;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class AdminProductDto {
    @NotBlank
    @Length(min = 4)
    private String name;
    @NotBlank
    @Length(min = 4)
    private String category;
    @NotBlank
    @Length(min = 4)
    private String description;
    @NotBlank
    @Length(min = 7)
    private String img;
    @NotNull
    @Min(0)
    private BigDecimal price;
    private AdminProductCurrency currency;
}