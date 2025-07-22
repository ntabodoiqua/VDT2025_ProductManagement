package com.vdt2025.vdt2025_project_management.service;

import com.vdt2025.vdt2025_project_management.dto.request.user.UserCreationRequest;
import com.vdt2025.vdt2025_project_management.dto.response.UserResponse;

public interface UserService {

    public UserResponse createUser(UserCreationRequest request);

    public UserResponse getMyInfo();


}
