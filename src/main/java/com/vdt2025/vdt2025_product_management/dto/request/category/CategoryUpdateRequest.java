package com.vdt2025.vdt2025_product_management.dto.request.category;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryUpdateRequest {
    String name;
    String description;
    String imageName;
}
