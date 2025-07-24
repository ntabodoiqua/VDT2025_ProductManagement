package com.vdt2025.vdt2025_product_management.specification;

import com.vdt2025.vdt2025_product_management.dto.request.product.ProductFilterRequest;
import com.vdt2025.vdt2025_product_management.entity.Category;
import com.vdt2025.vdt2025_product_management.entity.Product;
import com.vdt2025.vdt2025_product_management.entity.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<Product> withFilter(ProductFilterRequest filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getName() != null && !filter.getName().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
            }
            if (filter.getCategoryId() != null && !filter.getCategoryId().isBlank()) {
                Join<Product, Category> categoryJoin = root.join("category");
                predicates.add(cb.equal(categoryJoin.get("id"), filter.getCategoryId()));
            }
            if (filter.getCreatedById() != null && !filter.getCreatedById().isBlank()) {
                Join<Product, User> userJoin = root.join("createdBy");
                predicates.add(cb.equal(userJoin.get("id"), filter.getCreatedById()));
            }
            if (filter.getIsActive() != null) {
                predicates.add(cb.equal(root.get("isActive"), filter.getIsActive()));
            }
            if (filter.getPriceFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.getPriceFrom()));
            }
            if (filter.getPriceTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.getPriceTo()));
            }
            if (filter.getCreatedFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), filter.getCreatedFrom()));
            }
            if (filter.getCreatedTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), filter.getCreatedTo()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
