# GlobalExceptionHandler - Tài liệu chi tiết

## Tổng quan

`GlobalExceptionHandler` là một component quan trọng trong Spring Boot application, được thiết kế để xử lý tập trung tất cả các exception xảy ra trong ứng dụng. Class này sử dụng annotation `@ControllerAdvice` để Spring Boot có thể nhận diện và sử dụng nó như một global exception handler.

## Kiến trúc và Nguyên tắc

### 1. Centralized Exception Handling
- **Mục đích**: Tập trung xử lý exception thay vì xử lý rải rác trong từng controller
- **Lợi ích**: Đảm bảo tính nhất quán trong response format và error handling logic
- **Pattern**: Global Exception Handler Pattern

### 2. Structured Error Response
Tất cả các exception đều được chuyển đổi thành format `ApiResponse` nhất quán:
```java
ApiResponse {
    code: String,      // Mã lỗi định danh
    message: String,   // Thông báo lỗi
    result: Object     // Dữ liệu kèm theo (nếu có)
}
```

## Chi tiết các Exception Handler

### 1. RuntimeException Handler
```java
@ExceptionHandler(value = Exception.class)
ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception)
```

**Chức năng**: Xử lý tất cả các exception chưa được handle cụ thể
- **Scope**: Catch-all handler cho các exception không được xử lý riêng biệt
- **Error Code**: `UNCATEGORIZED_EXCEPTION`
- **HTTP Status**: 400 Bad Request
- **Logging**: Ghi log error level với full stack trace
- **Sử dụng**: Fallback mechanism để đảm bảo không có exception nào bị bỏ sót

### 2. AppException Handler
```java
@ExceptionHandler(value = AppException.class)
ResponseEntity<ApiResponse> handlingAppException(AppException exception)
```

**Chức năng**: Xử lý các business logic exception được định nghĩa trong ứng dụng
- **Custom Exception**: `AppException` là exception tùy chỉnh chứa `ErrorCode`
- **Dynamic Status Code**: HTTP status code được lấy từ `errorCode.getStatusCode()`
- **Structured Response**: Code và message được mapping từ ErrorCode enum
- **Use Case**: Xử lý các lỗi nghiệp vụ như "User not found", "Invalid permissions", v.v.

### 3. AccessDeniedException Handler
```java
@ExceptionHandler(value = AccessDeniedException.class)
ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException exception)
```

**Chức năng**: Xử lý các lỗi liên quan đến authorization
- **Security Context**: Xử lý lỗi từ Spring Security
- **Error Code**: `ErrorCode.UNAUTHORIZED`
- **HTTP Status**: Được định nghĩa trong ErrorCode.UNAUTHORIZED
- **Builder Pattern**: Sử dụng builder pattern cho ApiResponse
- **Security**: Không log chi tiết để tránh information leakage

### 4. MethodArgumentNotValidException Handler
```java
@ExceptionHandler(value = MethodArgumentNotValidException.class)
ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception)
```

**Chức năng**: Xử lý các lỗi validation từ Bean Validation API
- **Validation Context**: Xử lý lỗi từ `@Valid`, `@Min`, `@Max`, `@NotBlank`, `@NotNull`, v.v.
- **Dynamic Error Mapping**: Mapping từ validation message sang ErrorCode enum
- **Attribute Substitution**: Thay thế placeholder trong error message bằng giá trị thực
- **Fallback Mechanism**: Sử dụng `INVALID_KEY` nếu không tìm thấy ErrorCode tương ứng

**Chi tiết xử lý:**
1. Lấy `defaultMessage` từ field error
2. Cố gắng parse thành `ErrorCode` enum
3. Extract constraint attributes từ `ConstraintViolation`
4. Thay thế placeholder trong message
5. Trả về response với thông tin chi tiết

### 5. DataIntegrityViolationException Handler
```java
@ExceptionHandler(value = DataIntegrityViolationException.class)
ResponseEntity<ApiResponse> handlingDataIntegrityViolationException(DataIntegrityViolationException exception)
```

**Chức năng**: Xử lý các lỗi ràng buộc dữ liệu từ database
- **Database Context**: Xử lý lỗi từ JPA/Hibernate
- **Common Cases**: Duplicate key, foreign key constraint, not null constraint
- **Error Code**: `DATA_INTEGRITY_VIOLATION`
- **Logging Level**: WARN (không phải ERROR) vì thường là lỗi do user input
- **Security**: Không expose chi tiết database structure

## Utility Methods

### mapAttributes Method
```java
private String mapAtributes(String message, Map<String, Object> attributes)
```

**Chức năng**: Thay thế placeholder trong error message
- **Current Support**: Chỉ hỗ trợ `{min}` placeholder
- **Extensible**: Có thể mở rộng để hỗ trợ thêm các attribute khác
- **String Replacement**: Sử dụng `String.replace()` để thay thế placeholder

## Logging Strategy

### Log Levels
- **ERROR**: RuntimeException (unexpected errors)
- **WARN**: DataIntegrityViolationException (expected but problematic)
- **ERROR**: Validation parsing failures
- **NO LOG**: AppException, AccessDeniedException (expected business flows)

### Log Content
- **Full Stack Trace**: Cho unexpected exceptions
- **Contextual Info**: Cho validation errors
- **Security Consideration**: Không log sensitive information

## Design Patterns Sử Dụng

### 1. Chain of Responsibility
Các exception handler được Spring Boot sắp xếp theo độ ưu tiên từ cụ thể đến chung chung.

### 2. Template Method
Tất cả handler đều follow cùng một template:
1. Extract error information
2. Create ApiResponse
3. Set appropriate HTTP status
4. Return ResponseEntity

### 3. Builder Pattern
Sử dụng trong một số handler để tạo ApiResponse.

## Kết luận

`GlobalExceptionHandler` là một component well-designed cho việc xử lý exception tập trung trong Spring Boot application. Nó đảm bảo:
- **Consistency**: Tất cả error response có cùng format
- **Maintainability**: Tập trung logic xử lý exception
- **Security**: Không expose sensitive information
- **Extensibility**: Dễ dàng thêm handler mới
- **Logging**: Ghi log phù hợp cho debugging

Class này thể hiện best practices trong exception handling và có thể được sử dụng làm template cho các project khác.