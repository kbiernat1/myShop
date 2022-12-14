package pl.kb.shop.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kb.shop.admin.model.AdminProduct;
import pl.kb.shop.admin.repository.AdminProductRepository;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final AdminProductRepository adminProductRepository;

    public Page<AdminProduct> getProducts(Pageable pageable) {
        return adminProductRepository.findAll(pageable);
    }

    public AdminProduct getProduct(Long id) {
        return adminProductRepository.findById(id).orElseThrow();
    }

    public AdminProduct createProduct(AdminProduct adminProduct) {
        return adminProductRepository.save(adminProduct);
    }

    public AdminProduct updateProduct(AdminProduct adminProduct) {
        return adminProductRepository.save(adminProduct);
    }
}
