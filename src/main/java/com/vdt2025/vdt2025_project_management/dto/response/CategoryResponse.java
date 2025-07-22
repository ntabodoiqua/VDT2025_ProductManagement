package com.vdt2025.vdt2025_project_management.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    String id;
    String name;
    String description;
    String imageName;
    UserResponse createdBy;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
