package com.vdt2025.vdt2025_product_management.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    // Lỗi xác thực (11xx)
    UNAUTHORIZED(1101, "You do not have permission", HttpStatus.FORBIDDEN),
    // Lỗi File (13xx)
    FILE_CANNOT_STORED(1301, "File cannot be stored", HttpStatus.INTERNAL_SERVER_ERROR),


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
    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
