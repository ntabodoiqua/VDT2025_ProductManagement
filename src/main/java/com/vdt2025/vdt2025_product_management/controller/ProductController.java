package com.vdt2025.vdt2025_product_management.controller;

import com.vdt2025.vdt2025_product_management.dto.request.product.ProductCreationRequest;
import com.vdt2025.vdt2025_product_management.dto.request.product.ProductFilterRequest;
import com.vdt2025.vdt2025_product_management.dto.request.product.ProductUpdateRequest;
import com.vdt2025.vdt2025_product_management.dto.response.ProductResponse;
import com.vdt2025.vdt2025_product_management.service.ProductServiceImp;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductServiceImp productService;

    // Thêm sản phẩm mới
    @PostMapping
    public ProductResponse createProduct(@RequestBody ProductCreationRequest request) {
        log.info("Creating new product: {}", request.getName());
        return productService.createProduct(request);
    }

    // Cập nhật thumbnail sản phẩm
    @PostMapping("/{productId}/thumbnail")
    public String updateProductThumbnail(
            @PathVariable String productId,
            @RequestParam("file") MultipartFile file) {
        log.info("Updating thumbnail for product ID: {}", productId);
        return productService.setProductThumbnail(productId, file);
    }

    // Cập nhật thông tin sản phẩm
    @PutMapping("/{productId}")
    public ProductResponse updateProduct(
            @PathVariable String productId,
            @RequestBody ProductUpdateRequest request) {
        log.info("Updating product with ID: {}", productId);
        return productService.updateProduct(productId, request);
    }

    // Xóa sản phẩm theo ID
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable String productId) {
        log.info("Deleting product with ID: {}", productId);
        productService.deleteProduct(productId);
    }

    // Lấy thông tin sản phẩm theo ID
    @GetMapping("/{productId}")
    public ProductResponse getProductById(@PathVariable String productId) {
        log.info("Fetching product with ID: {}", productId);
        return productService.getProductById(productId);
    }

    // Lấy danh sách sản phẩm với phân trang
    @GetMapping
    public Page<ProductResponse> getProducts(
            @ModelAttribute ProductFilterRequest filter,
            Pageable pageable) {
        log.info("Fetching products with filter: {}", filter);
        return productService.searchProducts(filter, pageable);
    }
}
