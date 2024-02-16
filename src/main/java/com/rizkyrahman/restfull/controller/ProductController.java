package com.rizkyrahman.restfull.controller;


import com.rizkyrahman.restfull.dto.request.respons.ControllerResponse;
import com.rizkyrahman.restfull.dto.request.respons.PageResponWrapper;
import com.rizkyrahman.restfull.entity.ProductEntity;
import com.rizkyrahman.restfull.service.implement.ProductServiceImpl;
import com.rizkyrahman.restfull.util.constans.ApiUrlConstans;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping(ApiUrlConstans.PRODUCT)
@Builder
public class ProductController {

    private ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<?> createNewProduct(@RequestBody ProductEntity product) {
        ProductEntity productEntity = productService.create(product);
        ControllerResponse<ProductEntity> response = ControllerResponse.

    }

    @GetMapping()
    public PageResponWrapper<ProductEntity> getAllProduct
            (@RequestParam(name = "page", defaultValue = "0") Integer page,
             @RequestParam(name = "size", defaultValue = "5") Integer size,
             @RequestParam(name = "sort-by", defaultValue = "name") String sortBy,
             @RequestParam(name = "direction", defaultValue = "ASC") String direction
            ) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProductEntity> productEntityPage = productService.pageProduct(pageable);
        return new PageResponWrapper<>(productEntityPage);
    }

    @GetMapping("{id}")
    public Optional<ProductEntity> findById(@PathVariable(name = "id") String id) {
        return productService.getById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable(name = "id") String id) {
        productService.delete(id);
    }

    @PutMapping("")
    public ProductEntity updateProduct(@RequestBody ProductEntity product) {
        productService.update(product);
        return product;
    }


}
