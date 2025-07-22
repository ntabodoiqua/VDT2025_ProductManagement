package com.vdt2025.vdt2025_project_management.dto.request.user;

import com.vdt2025.vdt2025_project_management.validator.DobConstrain;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String firstName;
    String lastName;
    String email;
    @DobConstrain(min = 16, message = "INVALID_DOB")
    LocalDate dob;
    String phone;
    String avatarName;
}
