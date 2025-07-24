package com.vdt2025.vdt2025_product_management.service;

import com.vdt2025.vdt2025_product_management.dto.request.role.RoleRequest;
import com.vdt2025.vdt2025_product_management.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {

    RoleResponse createRole(RoleRequest request);

    List<RoleResponse> getAllRoles();

    void delete(String roleName);

    RoleResponse getRoleByName(String roleName);

    RoleResponse updateRole(String roleName, RoleRequest request);

}
