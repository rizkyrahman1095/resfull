package com.rizkyrahman.restfull.service;

import com.rizkyrahman.restfull.entity.ProductEntity;
import com.rizkyrahman.restfull.repo.ProductRepo;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

        ProductEntity create(ProductEntity product);
        List<ProductEntity> getAll();
        Optional<ProductEntity> getById(String id);
        ProductEntity update(ProductEntity product);
        void delete(String id);

        Page<ProductEntity> pageProduct(Pageable pageable);


}
