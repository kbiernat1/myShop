package pl.kb.shop.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.kb.shop.admin.controller.dto.AdminProductDto;
import pl.kb.shop.admin.controller.dto.UploadResponse;
import pl.kb.shop.admin.model.AdminProduct;
import pl.kb.shop.admin.service.AdminProductService;
import pl.kb.shop.admin.service.AdminProductImageService;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
public class AdminProductController {

    private static final Long EMPTY_ID = null;
    private final AdminProductService adminProductService;
    private final AdminProductImageService adminProductImageService;

    @GetMapping("/admin/products")
    public Page<AdminProduct> getProducts(Pageable pageable) {
        return adminProductService.getProducts(pageable);
    }

    @GetMapping("/admin/products/{id}")
    public AdminProduct getProduct(@PathVariable Long id) {
        return adminProductService.getProduct(id);
    }

    @PostMapping("/admin/products")
    public AdminProduct createProduct(@RequestBody @Valid AdminProductDto adminProductDto) {
        return adminProductService.createProduct(mapAdminProduct(adminProductDto, EMPTY_ID));
    }

    @PutMapping("/admin/products/{id}")
    public AdminProduct updateProduct(@RequestBody @Valid AdminProductDto adminProductDto, @PathVariable Long id) {
        return adminProductService.updateProduct(mapAdminProduct(adminProductDto, id));
    }

    @DeleteMapping("/admin/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        adminProductService.deleteProduct(id);
    }

    @PostMapping("/admin/products/upload-image")
    public UploadResponse uploadImage(@RequestParam("file") MultipartFile multipartFile) {
        try(InputStream inputStream = multipartFile.getInputStream()) {
            String savedFileName = adminProductImageService.uploadImage(multipartFile.getOriginalFilename(), inputStream);
            return new UploadResponse(savedFileName);
        } catch (IOException e) {
            throw new RuntimeException("Coś poszło nie tak podczas wgrywania pliku.", e);
        }
    }

    @GetMapping("/data/productImage/{fileName}")
    public ResponseEntity<Resource> serveFiles(@PathVariable String fileName) throws IOException {
        Resource file = adminProductImageService.serveFiles(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Path.of(fileName)))
                .body(file);
    }

    private AdminProduct mapAdminProduct(AdminProductDto adminProductDto, Long id) {
        return AdminProduct.builder()
                .id(id)
                .name(adminProductDto.getName())
                .category(adminProductDto.getCategory())
                .description(adminProductDto.getDescription())
                .img(adminProductDto.getImg())
                .price(adminProductDto.getPrice())
                .currency(adminProductDto.getCurrency())
                .build();
    }
}
