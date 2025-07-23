package com.vdt2025.vdt2025_product_management.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum ErrorCode {
    // Lỗi xác thực (11xx)
    UNAUTHORIZED(1101, "You do not have permission", HttpStatus.FORBIDDEN),
    UNAUTHENTICATED(1102, "You are not authenticated", HttpStatus.UNAUTHORIZED),

    // Lỗi người dùng (12xx)
    USER_NOT_FOUND(1201, "User not found", HttpStatus.NOT_FOUND),
    USER_DISABLED(1202, "User is disabled", HttpStatus.FORBIDDEN),
    USER_EXISTED(1203, "User already exists", HttpStatus.CONFLICT),
    // Lỗi File (13xx)
    FILE_CANNOT_STORED(1301, "File cannot be stored", HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_NOT_FOUND(1302, "File cannot be found", HttpStatus.NOT_FOUND),

    // Lỗi vai trò (14xx)
    ROLE_NOT_FOUND(1401, "Role not found", HttpStatus.NOT_FOUND),
    // Lỗi khác (99xx)
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(9998, "Invalid key", HttpStatus.BAD_REQUEST),
    DATA_INTEGRITY_VIOLATION(9997, "Data integrity violation", HttpStatus.CONFLICT),
    ;
    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    private final int code;

    private final String message;

    private final HttpStatusCode statusCode;

}
