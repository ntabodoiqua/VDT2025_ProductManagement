package com.vdt2025.vdt2025_product_management.repository;

import com.vdt2025.vdt2025_product_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);

    User findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByPhoneNumber(String phoneNumber);
}
