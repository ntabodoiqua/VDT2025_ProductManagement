package com.vdt2025.vdt2025_product_management.service;

import com.vdt2025.vdt2025_product_management.dto.request.category.CategoryCreationRequest;
import com.vdt2025.vdt2025_product_management.dto.request.category.CategoryFilterRequest;
import com.vdt2025.vdt2025_product_management.dto.request.category.CategoryUpdateRequest;
import com.vdt2025.vdt2025_product_management.dto.response.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface CategoryService {

    CategoryResponse createCategory(CategoryCreationRequest request);

    CategoryResponse getCategoryById(String id);

    CategoryResponse updateCategory(String id, CategoryUpdateRequest request);

    String setCategoryThumbnail(String id, MultipartFile file);

    void deleteCategory(String id);

    Page<CategoryResponse> searchCategories(CategoryFilterRequest filter, Pageable pageable);
}
