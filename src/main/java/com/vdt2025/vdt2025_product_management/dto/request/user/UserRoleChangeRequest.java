package com.vdt2025.vdt2025_product_management.dto.request.user;

import com.vdt2025.vdt2025_product_management.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRoleChangeRequest {
    String roleName;
}
