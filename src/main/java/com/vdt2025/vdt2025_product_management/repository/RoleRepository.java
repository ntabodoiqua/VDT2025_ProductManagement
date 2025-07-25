package com.vdt2025.vdt2025_product_management.repository;

import com.vdt2025.vdt2025_product_management.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @Query(value = "SELECT COUNT(*) FROM users WHERE role_id = :roleId", nativeQuery = true)
    Long countUsersWithRole(String roleId);
}
