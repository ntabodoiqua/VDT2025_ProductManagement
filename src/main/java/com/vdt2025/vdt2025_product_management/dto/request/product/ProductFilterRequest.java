package com.vdt2025.vdt2025_product_management.dto.request.product;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductFilterRequest {
    String name;
    String categoryId;
    String createdById;
    Boolean isActive;

    BigDecimal priceFrom;
    BigDecimal priceTo;

    Integer quantityFrom;
    Integer quantityTo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    String createdFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    String createdTo;
}
