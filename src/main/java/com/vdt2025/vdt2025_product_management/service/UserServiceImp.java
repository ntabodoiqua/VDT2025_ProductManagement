package com.vdt2025.vdt2025_product_management.service;

import com.vdt2025.vdt2025_product_management.constant.PredefinedRole;
import com.vdt2025.vdt2025_product_management.dto.request.user.UserCreationRequest;
import com.vdt2025.vdt2025_product_management.dto.request.user.UserUpdateRequest;
import com.vdt2025.vdt2025_product_management.dto.response.UserResponse;
import com.vdt2025.vdt2025_product_management.entity.Role;
import com.vdt2025.vdt2025_product_management.entity.User;
import com.vdt2025.vdt2025_product_management.exception.AppException;
import com.vdt2025.vdt2025_product_management.exception.ErrorCode;
import com.vdt2025.vdt2025_product_management.mapper.UserMapper;
import com.vdt2025.vdt2025_product_management.repository.RoleRepository;
import com.vdt2025.vdt2025_product_management.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImp implements UserService {
    RoleRepository roleRepository;
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(UserCreationRequest request) {
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        // Kiểm tra xem vai trò đã được định nghĩa chưa, nếu chưa thì tạo mới
        if (roleRepository.findById(PredefinedRole.GUEST_ROLE).isEmpty()) {
            Role guestRole = new Role();
            guestRole.setName(PredefinedRole.GUEST_ROLE);
            guestRole.setDescription("Guest role to be assigned to new users. Can view products and categories.");
            roleRepository.save(guestRole);
        }
        // Gán vai trò cho người dùng
        user.setRole(roleRepository.findById(PredefinedRole.GUEST_ROLE)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND)));
        // Lưu người dùng vào cơ sở dữ liệu
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse getMyInfo() {
        // Implementation for getting user info
        return null; // Placeholder return
    }

    @Override
    public String changeMyPassword(String oldPassword, String newPassword) {
        // Implementation for changing user password
        return null; // Placeholder return
    }

    @Override
    public String setMyAvatar(MultipartFile file) {
        // Implementation for setting user avatar
        return null; // Placeholder return
    }

    @Override
    public UserResponse updateMyInfo(UserUpdateRequest request) {
        // Implementation for updating user info
        return null; // Placeholder return
    }

    @Override
    public String deleteMyAccount() {
        // Implementation for deleting user account
        return null; // Placeholder return
    }

    @Override
    public String disableMyAccount() {
        // Implementation for disabling user account
        return null; // Placeholder return
    }

}
