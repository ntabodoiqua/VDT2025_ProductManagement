package com.vdt2025.vdt2025_project_management.mapper;

import com.vdt2025.vdt2025_project_management.dto.request.user.UserCreationRequest;
import com.vdt2025.vdt2025_project_management.dto.request.user.UserUpdateRequest;
import com.vdt2025.vdt2025_project_management.dto.response.UserResponse;
import com.vdt2025.vdt2025_project_management.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
