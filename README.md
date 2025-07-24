# VDT 2025 - Hệ Thống Quản Lý Sản Phẩm

## Tổng Quan

Hệ thống quản lý sản phẩm VDT 2025 là một ứng dụng web được phát triển bằng Spring Boot 3, cung cấp giải pháp toàn diện cho việc quản lý sản phẩm, danh mục và người dùng. Hệ thống được thiết kế với kiến trúc RESTful API, tích hợp bảo mật JWT và hỗ trợ upload file.

### Mục Tiêu Dự Án

- Cung cấp nền tảng quản lý sản phẩm hiệu quả
- Phân quyền người dùng rõ ràng (Admin, Manager, User)
- Giao diện API thân thiện và dễ sử dụng
- Bảo mật cao với JWT Authentication

## Tính Năng

### Quản Lý Xác Thực & Phân Quyền

- **Đăng ký/Đăng nhập**: Hỗ trợ tạo tài khoản và xác thực người dùng
- **JWT Token**: Bảo mật với JSON Web Token, hỗ trợ refresh token
- **Phân quyền**: 3 vai trò chính (ADMIN, MANAGER, USER)
- **Quản lý phiên**: Logout và vô hiệu hóa token

### Quản Lý Người Dùng

- **Thông tin cá nhân**: Xem và cập nhật thông tin cá nhân
- **Đổi mật khẩu**: Thay đổi mật khẩu an toàn
- **Avatar**: Upload và quản lý ảnh đại diện
- **Vô hiệu hóa tài khoản**: Tự vô hiệu hóa tài khoản

### Quản Lý Admin

- **Quản lý người dùng**: CRUD operations cho tất cả người dùng
- **Phân quyền**: Cập nhật vai trò người dùng
- **Kích hoạt/Vô hiệu hóa**: Quản lý trạng thái tài khoản
- **Tìm kiếm và lọc**: Tìm kiếm người dùng theo nhiều tiêu chí

### Quản Lý Danh Mục

- **CRUD Danh mục**: Tạo, đọc, cập nhật, xóa danh mục sản phẩm
- **Upload hình ảnh**: Quản lý thumbnail cho danh mục
- **Phân quyền**: Chỉ Admin/Manager mới có thể quản lý
- **Tìm kiếm**: Lọc danh mục theo tên và người tạo

### Quản Lý Sản Phẩm

- **CRUD Sản phẩm**: Quản lý toàn diện thông tin sản phẩm
- **Upload hình ảnh**: Quản lý thumbnail sản phẩm
- **Phân loại**: Gán sản phẩm vào danh mục
- **Tìm kiếm nâng cao**: Lọc theo nhiều tiêu chí
- **Phân trang**: Hiển thị danh sách có phân trang

### Quản Lý File

- **Upload file**: Hỗ trợ upload hình ảnh (max 10MB)
- **Lưu trữ**: Lưu file trong thư mục `uploads/`
- **Bảo mật**: Kiểm tra định dạng và kích thước file

## Cấu Trúc Dự Án

```
vdt2025_project_management/
├── src/main/java/com/vdt2025/vdt2025_product_management/
│   ├── configuration/          # Cấu hình Spring Boot
│   │   ├── ApplicationInitConfig.java
│   │   ├── CustomJwtDecoder.java
│   │   ├── JwtAuthenticationEntryPoint.java
│   │   ├── OpenApiConfig.java
│   │   └── SecurityConfig.java
│   ├── constant/              # Hằng số
│   │   └── PredefinedRole.java
│   ├── controller/            # REST Controllers
│   │   ├── AdminController.java
│   │   ├── AuthenticationController.java
│   │   ├── CategoryController.java
│   │   ├── ProductController.java
│   │   ├── RoleController.java
│   │   └── UserController.java
│   ├── dto/                   # Data Transfer Objects
│   │   ├── request/           # Request DTOs
│   │   └── response/          # Response DTOs
│   ├── entity/                # JPA Entities
│   │   ├── Category.java
│   │   ├── InvalidatedToken.java
│   │   ├── Product.java
│   │   ├── Role.java
│   │   └── User.java
│   ├── exception/             # Exception Handling
│   ├── mapper/                # MapStruct Mappers
│   ├── repository/            # JPA Repositories
│   ├── service/               # Business Logic
│   ├── specification/         # JPA Specifications for filtering
│   └── validator/             # Custom Validators
├── src/main/resources/
│   ├── application.yaml       # Cấu hình ứng dụng
│   ├── static/               # Static resources
│   └── templates/            # Templates (nếu có)
├── uploads/                  # Thư mục lưu file upload
├── db.sql                   # Database schema
├── pom.xml                  # Maven dependencies
└── README.md               # Tài liệu dự án
```

## Yêu Cầu Hệ Thống

### Môi Trường Phát Triển

- **Java**: JDK 21 trở lên
- **Maven**: 3.6.0 trở lên
- **IDE**: IntelliJ IDEA / Eclipse / VS Code

### Cơ Sở Dữ Liệu

- **PostgreSQL**: 12.0 trở lên
- **Database Name**: `product_management`
- **Port**: 5432 (mặc định)

### Phần Mềm Hỗ Trợ

- **Git**: Để clone repository
- **Postman**: Để test API (tùy chọn)
- **DBeaver/pgAdmin**: Để quản lý database (tùy chọn)

## Cài Đặt và Chạy

### 1. Clone Repository

```bash
git clone https://github.com/ntabodoiqua/VDT2025_ProductManagement.git
cd VDT2025_ProductManagement
```

### 2. Cài Đặt PostgreSQL

1. Tải và cài đặt PostgreSQL từ [postgresql.org](https://www.postgresql.org/download/)
2. Tạo database mới:

```sql
CREATE DATABASE product_management;
```

### 3. Cấu Hình Database

Cập nhật thông tin database trong `src/main/resources/application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/product_management
    username: your_username
    password: your_password
```

### 4. Import Database Schema

```bash
psql -U your_username -d product_management -f db.sql
```

### 5. Build và Chạy Ứng Dụng

```bash
# Build project
mvn clean install

# Chạy ứng dụng
mvn spring-boot:run
```

### 6. Truy Cập Ứng Dụng

- **Base URL**: `http://localhost:8080/vdt`
- **Swagger UI**: `http://localhost:8080/vdt/swagger-ui.html`
- **API Docs**: `http://localhost:8080/vdt/v3/api-docs`

## Hướng Dẫn Sử Dụng

### Xác Thực

1. **Đăng ký tài khoản mới**:

   ```http
   POST /vdt/users
   Content-Type: application/json

   {
     "username": "user123",
     "password": "password123",
     "firstName": "John",
     "lastName": "Doe",
     "email": "john.doe@example.com"
   }
   ```

2. **Đăng nhập**:

   ```http
   POST /vdt/auth/token
   Content-Type: application/json

   {
     "username": "user123",
     "password": "password123"
   }
   ```

3. **Sử dụng token**: Thêm header `Authorization: Bearer <token>` cho các request cần xác thực

### 👤 Quản Lý Tài Khoản

- **Xem thông tin cá nhân**: `GET /vdt/users/myInfo`
- **Cập nhật thông tin**: `PUT /vdt/users/myInfo`
- **Đổi mật khẩu**: `PUT /vdt/users/changePassword`
- **Upload avatar**: `POST /vdt/users/avatar`

### Quản Lý Danh Mục (Admin/Manager)

- **Danh sách danh mục**: `GET /vdt/categories`
- **Tạo danh mục**: `POST /vdt/categories`
- **Cập nhật danh mục**: `PUT /vdt/categories/{id}`
- **Xóa danh mục**: `DELETE /vdt/categories/{id}`
- **Upload thumbnail**: `POST /vdt/categories/{id}/thumbnail`

### Quản Lý Sản Phẩm

- **Danh sách sản phẩm**: `GET /vdt/products`
- **Tạo sản phẩm**: `POST /vdt/products`
- **Cập nhật sản phẩm**: `PUT /vdt/products/{id}`
- **Xóa sản phẩm**: `DELETE /vdt/products/{id}`
- **Upload thumbnail**: `POST /vdt/products/{id}/thumbnail`

### Quản Lý Admin (Admin only)

- **Danh sách người dùng**: `GET /vdt/admin`
- **Cập nhật người dùng**: `PUT /vdt/admin/{userId}`
- **Phân quyền**: `PUT /vdt/admin/{userId}/role`
- **Kích hoạt/Vô hiệu hóa**: `PUT /vdt/admin/{userId}/enable` / `PUT /vdt/admin/{userId}/disable`

## API Documentation

### Swagger UI

Truy cập Swagger UI tại: `http://localhost:8080/vdt/swagger-ui.html`

Swagger cung cấp:

- Danh sách đầy đủ các API endpoints
- Mô tả chi tiết request/response
- Công cụ test API trực tiếp
- Schema documentation

### Các Endpoint Chính

#### Authentication (`/auth`)

| Method | Endpoint           | Mô tả          |
| ------ | ------------------ | -------------- |
| POST   | `/auth/token`      | Đăng nhập      |
| POST   | `/auth/introspect` | Kiểm tra token |
| POST   | `/auth/refresh`    | Làm mới token  |
| POST   | `/auth/logout`     | Đăng xuất      |

#### Users (`/users`)

| Method | Endpoint                | Mô tả              |
| ------ | ----------------------- | ------------------ |
| POST   | `/users`                | Đăng ký tài khoản  |
| GET    | `/users/myInfo`         | Thông tin cá nhân  |
| PUT    | `/users/myInfo`         | Cập nhật thông tin |
| PUT    | `/users/changePassword` | Đổi mật khẩu       |
| POST   | `/users/avatar`         | Upload avatar      |

#### Categories (`/categories`)

| Method | Endpoint                     | Mô tả              |
| ------ | ---------------------------- | ------------------ |
| GET    | `/categories`                | Danh sách danh mục |
| POST   | `/categories`                | Tạo danh mục       |
| GET    | `/categories/{id}`           | Chi tiết danh mục  |
| PUT    | `/categories/{id}`           | Cập nhật danh mục  |
| DELETE | `/categories/{id}`           | Xóa danh mục       |
| POST   | `/categories/{id}/thumbnail` | Upload thumbnail   |

#### Products (`/products`)

| Method | Endpoint                   | Mô tả              |
| ------ | -------------------------- | ------------------ |
| GET    | `/products`                | Danh sách sản phẩm |
| POST   | `/products`                | Tạo sản phẩm       |
| GET    | `/products/{id}`           | Chi tiết sản phẩm  |
| PUT    | `/products/{id}`           | Cập nhật sản phẩm  |
| DELETE | `/products/{id}`           | Xóa sản phẩm       |
| POST   | `/products/{id}/thumbnail` | Upload thumbnail   |

#### Admin (`/admin`)

| Method | Endpoint                  | Mô tả                |
| ------ | ------------------------- | -------------------- |
| GET    | `/admin`                  | Danh sách người dùng |
| GET    | `/admin/{userId}`         | Chi tiết người dùng  |
| PUT    | `/admin/{userId}`         | Cập nhật người dùng  |
| PUT    | `/admin/{userId}/role`    | Phân quyền           |
| PUT    | `/admin/{userId}/enable`  | Kích hoạt            |
| PUT    | `/admin/{userId}/disable` | Vô hiệu hóa          |

### Response Format

Tất cả API response đều có format chung:

```json
{
  "code": 1000,
  "message": "Success",
  "result": {
    /* data */
  }
}
```

### Authentication

- Sử dụng JWT Bearer Token
- Header: `Authorization: Bearer <token>`
- Token có thời hạn 1 giờ (3600 seconds)
- Refresh token có thời hạn 24 giờ (86400 seconds)

### Phân Trang

Các endpoint trả về danh sách đều hỗ trợ phân trang:

- `page`: Số trang (bắt đầu từ 0)
- `size`: Kích thước trang
- `sort`: Sắp xếp (ví dụ: `name,asc`)

Example: `GET /vdt/products?page=0&size=10&sort=name,asc`

---

**© VDT 2025 - Nguyễn Thế Anh.**
