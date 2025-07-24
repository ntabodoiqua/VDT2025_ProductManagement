package com.vdt2025.vdt2025_product_management.dto.request.product;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdateRequest {
    String name;
    String description;
    String image_name;
    BigDecimal price;
    int quantity;
    String categoryId;
    Boolean isActive;
}
