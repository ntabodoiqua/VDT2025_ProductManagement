package com.vdt2025.vdt2025_product_management.mapper;

import com.vdt2025.vdt2025_product_management.dto.request.role.RoleRequest;
import com.vdt2025.vdt2025_product_management.dto.response.RoleResponse;
import com.vdt2025.vdt2025_product_management.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}
