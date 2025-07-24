package com.vdt2025.vdt2025_product_management.configuration;

import com.vdt2025.vdt2025_product_management.constant.PredefinedRole;
import com.vdt2025.vdt2025_product_management.entity.Category;
import com.vdt2025.vdt2025_product_management.entity.Role;
import com.vdt2025.vdt2025_product_management.entity.User;
import com.vdt2025.vdt2025_product_management.repository.CategoryRepository;
import com.vdt2025.vdt2025_product_management.repository.RoleRepository;
import com.vdt2025.vdt2025_product_management.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository,
                                        RoleRepository roleRepository,
                                        CategoryRepository categoryRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()){
                Role adminRole = new Role();
                adminRole.setName("ADMIN");
                adminRole.setDescription("Administrator role with full access");
                roleRepository.save(adminRole);
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .email("admin@gmail.com")
                        .phone("0123456789")
                        .role(adminRole)
                        .build();
                userRepository.save(user);
                log.warn("Admin user has been created with default password: admin, please change it");
            }
            // Tạo category mặc định nếu chưa có
            User adminUser = userRepository.findByUsername("admin")
                    .orElseThrow(() -> new RuntimeException("Admin user not found"));
            String defaultCategoryName = "Chưa phân loại";
            if (!categoryRepository.existsByName(defaultCategoryName)) {
                Category defaultCategory = Category.builder()
                        .name(defaultCategoryName)
                        .description("Danh mục mặc định cho các sản phẩm chưa được phân loại")
                        .createdBy(adminUser)
                        .build();
                categoryRepository.save(defaultCategory);
            }
        };
    }
}
