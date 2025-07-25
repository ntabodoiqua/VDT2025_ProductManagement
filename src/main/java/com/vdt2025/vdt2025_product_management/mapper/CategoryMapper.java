package com.vdt2025.vdt2025_product_management.mapper;

import com.vdt2025.vdt2025_product_management.dto.request.category.CategoryCreationRequest;
import com.vdt2025.vdt2025_product_management.dto.request.category.CategoryUpdateRequest;
import com.vdt2025.vdt2025_product_management.dto.response.CategoryResponse;
import com.vdt2025.vdt2025_product_management.entity.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryCreationRequest request);
    CategoryResponse toCategoryResponse(Category category);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategory(@MappingTarget Category category, CategoryUpdateRequest request);
}
