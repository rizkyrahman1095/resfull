package com.rizkyrahman.restfull.specification;


import com.rizkyrahman.restfull.dto.request.ProductSearchDto;
import com.rizkyrahman.restfull.entity.Customer;
import com.rizkyrahman.restfull.entity.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<Product> getProductSpecification(ProductSearchDto productSearchDto) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (productSearchDto.getProductName() != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + productSearchDto.getProductName() + "%"));
            }
            if (productSearchDto.getProductPrice() != null) {
                predicates.add(criteriaBuilder.like(root.get("price"), "%" + productSearchDto.getProductPrice() + "%"));
            }
            if (productSearchDto.getProductStock() != null) {
                predicates.add(criteriaBuilder.like(root.get("stock"), "%" + productSearchDto.getProductStock() + "%"));
            }
            Predicate[] predicates1 = predicates.toArray(new Predicate[predicates.size()]);
            return criteriaBuilder.and(predicates1);
        });
    }
}


