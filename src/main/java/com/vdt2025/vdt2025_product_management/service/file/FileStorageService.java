package com.vdt2025.vdt2025_product_management.service.file;

import com.vdt2025.vdt2025_product_management.repository.UploadedFileRepository;
import com.vdt2025.vdt2025_product_management.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileStorageService {
    UserRepository userRepository;
    UploadedFileRepository uploadedFileRepository;
    FileStorageProperties fileStorageProperties;

    public String storeFile(MultipartFile file) {
        try {
            String directory = fileStorageProperties.getDirectory();
            // Chuyển đổi đường dẫn đến thư mục lưu trữ thành đường dẫn tuyệt đối và chuẩn hóa
            Path dir = Paths.get(directory).toAbsolutePath().normalize();
            // Kiểm tra xem thư mục đã tồn tại chưa, nếu chưa thì tạo mới
            Files.createDirectory(dir);
            String originalFilename = file.getOriginalFilename();
            // Kiểm tra xem tên tệp có hợp lệ không, nếu không thì thay thế ký tự không hợp lệ bằng dấu gạch dưới
            String sanitizedFilename = originalFilename.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
            String fileName = UUID.randomUUID() + "_" + sanitizedFilename;
            Path targetLocation = dir.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            log.info("File stored successfully: {}", fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
