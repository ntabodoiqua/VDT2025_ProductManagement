package com.vdt2025.vdt2025_product_management.mapper;

import com.vdt2025.vdt2025_product_management.dto.request.product.ProductCreationRequest;
import com.vdt2025.vdt2025_product_management.dto.request.product.ProductUpdateRequest;
import com.vdt2025.vdt2025_product_management.dto.response.ProductResponse;
import com.vdt2025.vdt2025_product_management.entity.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, UserMapper.class})
public interface ProductMapper {
    Product toProduct(ProductCreationRequest request);
    
    @Mapping(source = "active", target = "active")
    ProductResponse toProductResponse(Product product);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProduct(@MappingTarget Product product, ProductUpdateRequest request);
}
