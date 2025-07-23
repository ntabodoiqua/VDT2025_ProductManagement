package com.vdt2025.vdt2025_product_management.service.file;

import com.vdt2025.vdt2025_product_management.entity.UploadedFile;
import com.vdt2025.vdt2025_product_management.exception.AppException;
import com.vdt2025.vdt2025_product_management.exception.ErrorCode;
import com.vdt2025.vdt2025_product_management.repository.UploadedFileRepository;
import com.vdt2025.vdt2025_product_management.repository.UserRepository;
import org.springframework.core.io.Resource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileStorageService {
    UserRepository userRepository;
    UploadedFileRepository uploadedFileRepository;
    FileStorageProperties fileStorageProperties;

    // Lưu trữ File
    public String storeFile(MultipartFile file) {
        try {
            String directory = fileStorageProperties.getDirectory();
            // Chuyển đổi đường dẫn đến thư mục lưu trữ thành đường dẫn tuyệt đối và chuẩn hóa
            Path dir = Paths.get(directory).toAbsolutePath().normalize();
            // Kiểm tra xem thư mục đã tồn tại chưa, nếu chưa thì tạo mới
            Files.createDirectories(dir);
            String originalFilename = file.getOriginalFilename();
            // Kiểm tra xem tên tệp có hợp lệ không, nếu không thì thay thế ký tự không hợp lệ bằng dấu gạch dưới
            String sanitizedFilename = originalFilename.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
            String fileName = UUID.randomUUID() + "_" + sanitizedFilename;
            Path targetLocation = dir.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            log.info("File stored successfully: {}", fileName);
            // Luu thông tin file vào cơ sở dữ liệu
            UploadedFile uploadedFile = UploadedFile.builder()
                    .fileName(fileName)
                    .originalFileName(originalFilename)
                    .fileType(file.getContentType())
                    .fileSize(file.getSize())
                    .uploadedAt(LocalDateTime.now())
                    .uploadedBy(userRepository.findByUsername(
                            SecurityContextHolder.getContext().getAuthentication()
                                    .getName()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)
                    ))
                    .build();
            uploadedFileRepository.save(uploadedFile);
            return fileName;
        } catch (IOException e) {
            throw new AppException(ErrorCode.FILE_CANNOT_STORED);
        }
    }

    // Tải file
    public Resource loadFile(String fileName) {
        try {
            String directory = fileStorageProperties.getDirectory();
            Path filePath = Paths.get(directory).toAbsolutePath().normalize().resolve(fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new AppException(ErrorCode.FILE_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new AppException(ErrorCode.FILE_NOT_FOUND);
        }
    }

    // Xóa file
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deleteFile(String fileName) {

    }

}
