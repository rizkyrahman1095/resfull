package com.rizkyrahman.restfull.service.implement;

import com.rizkyrahman.restfull.dto.request.ProductSearchDto;
import com.rizkyrahman.restfull.entity.Product;
import com.rizkyrahman.restfull.repo.ProductRepo;
import com.rizkyrahman.restfull.service.ProductService;
import com.rizkyrahman.restfull.specification.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product create(Product product) {
        return productRepo.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    @Override
    public Product getById(String id) {
        Optional<Product> productEntity = productRepo.findById(id);
        return productEntity.orElse(null);
    }

    @Override
    public Product update(Product product) {
        return productRepo.save(product);

    }

    @Override
    public Product delete(String id) {
        Optional<Product> productEntity = productRepo.findById(id);
        if (productEntity.isPresent()) {
            productRepo.deleteById(id);
            return productEntity.get();
        }
        return null;
    }

    @Override
    public Page<Product> pageProduct(Pageable pageable, ProductSearchDto productSearchDto) {
        Specification<Product> productSpecification = ProductSpecification.getProductSpecification(productSearchDto);
        return productRepo.findAll(productSpecification,pageable);
    }

}
