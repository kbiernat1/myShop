package pl.kb.shop.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import pl.kb.shop.product.model.Product;
import pl.kb.shop.product.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> getProduct(@PageableDefault(size = 15) Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getProductBySlug(String slug) {
        return productRepository.findBySlug(slug).orElseThrow();
    }
}
