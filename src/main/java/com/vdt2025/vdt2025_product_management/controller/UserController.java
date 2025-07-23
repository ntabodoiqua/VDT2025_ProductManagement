package com.vdt2025.vdt2025_product_management.controller;

import com.vdt2025.vdt2025_product_management.dto.ApiResponse;
import com.vdt2025.vdt2025_product_management.dto.request.user.UserCreationRequest;
import com.vdt2025.vdt2025_product_management.dto.response.UserResponse;
import com.vdt2025.vdt2025_product_management.service.UserService;
import com.vdt2025.vdt2025_product_management.service.UserServiceImp;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserServiceImp userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

}
