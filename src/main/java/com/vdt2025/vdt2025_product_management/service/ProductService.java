package com.vdt2025.vdt2025_product_management.service;

import com.vdt2025.vdt2025_product_management.dto.request.category.CategoryFilterRequest;
import com.vdt2025.vdt2025_product_management.dto.request.product.ProductCreationRequest;
import com.vdt2025.vdt2025_product_management.dto.request.product.ProductFilterRequest;
import com.vdt2025.vdt2025_product_management.dto.request.product.ProductUpdateRequest;
import com.vdt2025.vdt2025_product_management.dto.response.CategoryResponse;
import com.vdt2025.vdt2025_product_management.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

    ProductResponse createProduct(ProductCreationRequest request);

    ProductResponse getProductById(String id);

    ProductResponse updateProduct(String id, ProductUpdateRequest request);

    String setProductThumbnail(String id, MultipartFile file);

    Page<ProductResponse> searchProducts(ProductFilterRequest filter, Pageable pageable);

    void deleteProduct(String id);
}
