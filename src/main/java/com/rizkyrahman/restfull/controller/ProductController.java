package com.rizkyrahman.restfull.controller;


import com.rizkyrahman.restfull.dto.request.ProductSearchDto;
import com.rizkyrahman.restfull.dto.respon.ControllerResponse;
import com.rizkyrahman.restfull.dto.respon.PageResponWrapper;
import com.rizkyrahman.restfull.entity.Product;
import com.rizkyrahman.restfull.service.implement.ProductServiceImpl;
import com.rizkyrahman.restfull.util.constans.ApiUrlConstans;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUrlConstans.PRODUCT)
public class ProductController {

    private ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<?> createNewProduct(@RequestBody Product product) {
        Product productEntity = productService.create(product);
        ControllerResponse<Product> response = ControllerResponse.<Product>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message(ApiUrlConstans.CREATE)
                .data(productEntity)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<?> getAllProduct
            (@RequestParam(name = "page", defaultValue = "0") Integer page,
             @RequestParam(name = "size", defaultValue = "5") Integer size,
             @RequestParam(name = "sort-by", defaultValue = "name") String sortBy,
             @RequestParam(name = "direction", defaultValue = "ASC") String direction,
             @RequestParam(name = "name", required = false) String name,
             @RequestParam(name = "price", required = false) Integer price,
             @RequestParam(name = "stock", required = false) Integer stock
            ) {

        ProductSearchDto productSearchDto = new ProductSearchDto(name, stock, price);
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productEntityPage = productService.pageProduct(pageable, productSearchDto);
        ControllerResponse<?> response = ControllerResponse.<PageResponWrapper<Product>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message(ApiUrlConstans.GETDATA)
                .data(new PageResponWrapper<>(productEntityPage))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") String id) {
        Product productEntity = productService.getById(id);

        ControllerResponse<Product> response = ControllerResponse.<Product>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message(ApiUrlConstans.GETDATA)
                .data(productEntity)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") String id) {
        Product deletedProduct = productService.delete(id);

        if (deletedProduct == null) {
            ControllerResponse<Product> response = ControllerResponse.<Product>builder()
                    .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message("Failed")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        ControllerResponse<Product> response = ControllerResponse.<Product>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message(ApiUrlConstans.DELLETE)
                .data(deletedProduct)
                .build();


        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.update(product);
        ControllerResponse<Product> response = ControllerResponse.<Product>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message(ApiUrlConstans.UPDATE)
                .data(updatedProduct)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


}
