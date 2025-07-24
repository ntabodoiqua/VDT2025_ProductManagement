package com.vdt2025.vdt2025_product_management.service;

import com.vdt2025.vdt2025_product_management.dto.request.product.ProductCreationRequest;
import com.vdt2025.vdt2025_product_management.dto.request.product.ProductFilterRequest;
import com.vdt2025.vdt2025_product_management.dto.request.product.ProductUpdateRequest;
import com.vdt2025.vdt2025_product_management.dto.response.ProductResponse;
import com.vdt2025.vdt2025_product_management.entity.Category;
import com.vdt2025.vdt2025_product_management.entity.Product;
import com.vdt2025.vdt2025_product_management.entity.User;
import com.vdt2025.vdt2025_product_management.exception.AppException;
import com.vdt2025.vdt2025_product_management.exception.ErrorCode;
import com.vdt2025.vdt2025_product_management.mapper.ProductMapper;
import com.vdt2025.vdt2025_product_management.repository.CategoryRepository;
import com.vdt2025.vdt2025_product_management.repository.ProductRepository;
import com.vdt2025.vdt2025_product_management.repository.UserRepository;
import com.vdt2025.vdt2025_product_management.service.file.FileStorageService;
import com.vdt2025.vdt2025_product_management.specification.ProductSpecification;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductServiceImp implements ProductService{
    private final UserRepository userRepository;
    ProductMapper productMapper;
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    FileStorageService fileStorageService;

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ProductResponse createProduct(ProductCreationRequest request) {
        // Kiểm tra xem sản phẩm đã tồn tại chưa
        if (productRepository.existsByName(request.getName())) {
            log.warn("Product {} already exists", request.getName());
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
        }
        // Kiểm tra xem danh mục có tồn tại không
        var category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        // Lấy thông tin người dùng hiện tại từ SecurityContext
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        // Tạo sản phẩm mới
        var product = productMapper.toProduct(request);
        product.setImage_name(null);
        product.setCreatedBy(user);
        product.setActive(true);
        product.setCategory(category);
        product = productRepository.save(product);
        log.info("Product {} created successfully", product.getName());
        return productMapper.toProductResponse(product);
    }

    @Override
    public ProductResponse getProductById(String id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return productMapper.toProductResponse(product);
    }

    // Hàm hỗ trợ kiểm tra quyền truy cập
    // Chỉ admin hoặc người tạo sản phẩm mới có quyền truy cập
    private boolean checkAccessRights(Product product) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        boolean isAdmin = currentUser.getRole().getName().equals("ADMIN");
        boolean isOwner = product.getCreatedBy().getUsername().equals(currentUser.getUsername());

        if (!isAdmin && !isOwner) {
            log.warn("User {} is not authorized to access product {}", currentUser.getUsername(), product.getName());
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
        return true;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public void deleteProduct(String id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        // Kiểm tra quyền truy cập
        if (!checkAccessRights(product)) {
            log.warn("User does not have access rights to delete product {}", product.getName());
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
        productRepository.delete(product);
        log.info("Product {} deleted successfully", product.getName());
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ProductResponse updateProduct(String id, ProductUpdateRequest request) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        // Kiểm tra quyền truy cập
        if (!checkAccessRights(product)) {
            log.warn("User does not have access rights to update product {}", product.getName());
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
        // Cập nhật thông tin sản phẩm
        productMapper.updateProduct(product, request);
        // Kiểm tra tên danh mục có tồn tại không
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
            product.setCategory(category);
        }
        product = productRepository.save(product);
        log.info("Product {} updated successfully", product.getName());
        return productMapper.toProductResponse(product);
    }

    @Override
    public Page<ProductResponse> searchProducts(ProductFilterRequest filter, Pageable pageable) {
        log.info("Searching products with filter: {}", filter);
        // Tạo truy vấn tìm kiếm với các điều kiện từ filter
        return productRepository.findAll(ProductSpecification.withFilter(filter),pageable)
                .map(productMapper::toProductResponse);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public String setProductThumbnail(String id, MultipartFile file) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        // Kiểm tra quyền truy cập
        if (!checkAccessRights(product)) {
            log.warn("User does not have access rights to update thumbnail for product {}", product.getName());
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
        // Kiểm tra loại tệp
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            log.warn("Invalid file type for thumbnail: {}", contentType);
            throw new AppException(ErrorCode.INVALID_IMAGE_TYPE);
        }
        // Lưu tệp và cập nhật tên ảnh
        String fileName = fileStorageService.storeFile(file);
        product.setImage_name(fileName);
        productRepository.save(product);
        log.info("Thumbnail for product {} updated successfully", product.getName());
        return fileName;
    }
}
