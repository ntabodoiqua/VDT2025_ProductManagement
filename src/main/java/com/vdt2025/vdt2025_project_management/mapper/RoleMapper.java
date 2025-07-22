package com.vdt2025.vdt2025_project_management.mapper;

import com.vdt2025.vdt2025_project_management.dto.request.role.RoleRequest;
import com.vdt2025.vdt2025_project_management.dto.response.RoleResponse;
import com.vdt2025.vdt2025_project_management.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}
