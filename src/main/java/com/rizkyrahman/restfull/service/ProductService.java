package com.rizkyrahman.restfull.service;

import com.rizkyrahman.restfull.dto.request.ProductSearchDto;
import com.rizkyrahman.restfull.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

        Product create(Product product);
        List<Product> getAll();
        Product getById(String id);
        Product update(Product product);
        Product delete(String id);
        Page<Product> pageProduct(Pageable pageable,ProductSearchDto productSearchDto);


}
