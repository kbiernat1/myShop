package pl.kb.shop.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kb.shop.category.model.Category;
import pl.kb.shop.category.model.CategoryProductsDto;
import pl.kb.shop.category.repository.CategoryRepository;
import pl.kb.shop.product.controller.dto.ProductListDto;
import pl.kb.shop.product.model.Product;
import pl.kb.shop.product.repository.ProductRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CategoryProductsDto getCategoriesWithProducts(String slug, Pageable pageable) {
        Category category = categoryRepository.findBySlug(slug);
        Page<Product> page = productRepository.findByCategoryId(category.getId(), pageable);
        List<ProductListDto> productListDtos = page.getContent().stream()
                .map(product -> ProductListDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .currency(product.getCurrency())
                        .img(product.getImg())
                        .slug(product.getSlug())
                        .build())
                .toList();
        return new CategoryProductsDto(category, new PageImpl<>(productListDtos, pageable, page.getTotalElements()));
    }
}