package com.vdt2025.vdt2025_product_management.exception;

import com.vdt2025.vdt2025_product_management.dto.ApiResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final String MIN_ATTRIBUTE = "min";

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {
        log.error("Exception: ", exception);
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(errorCode.getStatusCode()).body(
                ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build()
        );
    }
    // Xử lý các ràng buộc liên quan đến xác thực (Min, Max, NotBlank, NotNull, v.v.)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        Map<String, Object> attributes = null;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
            var constrainViolation = exception.getBindingResult()
                    .getAllErrors().getFirst().unwrap(ConstraintViolation.class);
            attributes = constrainViolation.getConstraintDescriptor().getAttributes();
        } catch (IllegalArgumentException e) {
            log.error("ErrorCode not found for enum key: {}", enumKey, e);
        }

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(Objects.nonNull(attributes) ?
            mapAtributes(errorCode.getMessage(), attributes) : errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    ResponseEntity<ApiResponse> handlingDataIntegrityViolationException(DataIntegrityViolationException exception) {
        log.warn("Data Integrity Violation: ", exception);
        ErrorCode errorCode = ErrorCode.DATA_INTEGRITY_VIOLATION;
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(apiResponse);
    }

    private String mapAtributes(String message, Map<String, Object> attributes) {
        String minValue = String.valueOf(attributes.get(MIN_ATTRIBUTE));
        return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
    }
}
