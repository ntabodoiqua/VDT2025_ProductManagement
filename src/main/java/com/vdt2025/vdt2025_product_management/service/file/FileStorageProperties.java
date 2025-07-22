package com.vdt2025.vdt2025_product_management.service.file;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileStorageProperties {
    String directory;
}
