package com.vdt2025.vdt2025_product_management.dto.response;

import com.vdt2025.vdt2025_product_management.entity.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    String id;
    String name;
    String description;
    String image_name;
    BigDecimal price;
    int quantity;
    CategoryResponse category;
    UserResponse createdBy;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    boolean active;
}
