package pl.kb.shop.product.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.kb.shop.product.model.Product;
import pl.kb.shop.product.service.ProductService;

import javax.validation.constraints.Pattern;

@RestController
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public Page<Product> getProducts(Pageable pageable) {
        return productService.getProduct(pageable);
    }

    @GetMapping("/products/{slug}")
    public Product getProductBySlug(@PathVariable
                                    @Pattern(regexp = "[a-z0-9\\-]+")
                                    @Length(max = 255)
                                            String slug) {
        return productService.getProductBySlug(slug);
    }
}
