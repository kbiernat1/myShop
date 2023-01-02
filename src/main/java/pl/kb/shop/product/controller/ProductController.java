package pl.kb.shop.product.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.kb.shop.common.dto.ProductListDto;
import pl.kb.shop.common.model.Product;
import pl.kb.shop.product.service.ProductService;

import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public Page<ProductListDto> getProducts(Pageable pageable) {
        Page<Product> products = productService.getProduct(pageable);
        List<ProductListDto> productListDtos = products.getContent().stream()
                .map(product -> ProductListDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .currency(product.getCurrency())
                        .img(product.getImg())
                        .slug(product.getSlug())
                        .build())
                .toList();
        return new PageImpl<>(productListDtos, pageable, products.getTotalElements());
    }

    @GetMapping("/products/{slug}")
    public Product getProductBySlug(@PathVariable
                                    @Pattern(regexp = "[a-z0-9\\-]+")
                                    @Length(max = 255)
                                            String slug) {
        return productService.getProductBySlug(slug);
    }
}
