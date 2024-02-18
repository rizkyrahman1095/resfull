package com.rizkyrahman.restfull.repo;

import com.rizkyrahman.restfull.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,String>, JpaSpecificationExecutor<Product> {
    List<Product>findProductByNameOrStock(String name,Integer stock);

}
