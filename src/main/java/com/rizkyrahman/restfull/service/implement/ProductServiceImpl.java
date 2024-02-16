package com.rizkyrahman.restfull.service.implement;

import com.rizkyrahman.restfull.entity.ProductEntity;
import com.rizkyrahman.restfull.repo.ProductRepo;
import com.rizkyrahman.restfull.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ProductEntity create(ProductEntity product) {
        return productRepo.save(product);
    }

    @Override
    public List<ProductEntity> getAll() {
        return productRepo.findAll();
    }

    @Override
    public Optional<ProductEntity> getById(String id) {
        return Optional.ofNullable(productRepo.findById(id).get());
    }

    @Override
    public ProductEntity update(ProductEntity product) {
//        delete(product.getId());
        return productRepo.save(product);

    }

    @Override
    public void delete(String id) {
        productRepo.deleteById(id);

    }

    @Override
    public Page<ProductEntity> pageProduct(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    public List<ProductEntity> findByName(String name){
       return productRepo.findProductEntitiesByNameLike(name);

    }
}
