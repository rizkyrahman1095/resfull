package com.rizkyrahman.restfull.repo;

import com.rizkyrahman.restfull.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<ProductEntity,String> {
    List<ProductEntity>findProductEntitiesByNameLike(String name);
}
