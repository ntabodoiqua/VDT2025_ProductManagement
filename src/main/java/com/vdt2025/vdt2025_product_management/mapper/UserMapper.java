package com.vdt2025.vdt2025_product_management.mapper;

import com.vdt2025.vdt2025_product_management.dto.request.user.UserCreationRequest;
import com.vdt2025.vdt2025_product_management.dto.request.user.UserUpdateRequest;
import com.vdt2025.vdt2025_product_management.dto.response.UserResponse;
import com.vdt2025.vdt2025_product_management.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    // Cập nhật thông tin người dùng từ request
    // Với các trường hợp không có trong request, sẽ giữ nguyên giá trị cũ
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
