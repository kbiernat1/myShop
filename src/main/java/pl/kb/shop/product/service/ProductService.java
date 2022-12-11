package pl.kb.shop.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kb.shop.product.model.Product;
import pl.kb.shop.product.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProduct() {
        return productRepository.findAll();
    }
}
