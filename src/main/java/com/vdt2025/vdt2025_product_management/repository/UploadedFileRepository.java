package com.vdt2025.vdt2025_product_management.repository;

import com.vdt2025.vdt2025_product_management.entity.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadedFileRepository extends JpaRepository<UploadedFile, String> {

}
