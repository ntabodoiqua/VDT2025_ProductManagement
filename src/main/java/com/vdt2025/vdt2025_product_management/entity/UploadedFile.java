package com.vdt2025.vdt2025_product_management.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class UploadedFile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false, unique = true)
    String fileName;
    String originalFileName;
    String fileType;
    Long fileSize;
    LocalDateTime uploadedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User uploadedBy;
}
