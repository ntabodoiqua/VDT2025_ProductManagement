package com.vdt2025.vdt2025_project_management.dto.response;

import com.vdt2025.vdt2025_project_management.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String firstName;
    String lastName;
    LocalDate dob;
    String phone;
    String email;
    String avatarName;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    RoleResponse role;
}
