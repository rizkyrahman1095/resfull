package com.rizkyrahman.restfull.controller;

import com.rizkyrahman.restfull.dto.respon.ControllerResponse;
import com.rizkyrahman.restfull.dto.respon.PageResponWrapper;
import com.rizkyrahman.restfull.entity.Customer;
import com.rizkyrahman.restfull.service.implement.CustomerServiceImpl;
import com.rizkyrahman.restfull.util.constans.ApiUrlConstans;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUrlConstans.CUSTOMER)
public class CustomerController {
    private CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public ResponseEntity<?> createNewCustomer(@RequestBody Customer customer) {
        Customer customers = customerService.create(customer);
        ControllerResponse<Customer> response = ControllerResponse.<Customer>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message(ApiUrlConstans.CREATE)
                .data(customers)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<?> getAllProduct
            (@RequestParam(name = "page", defaultValue = "0") Integer page,
             @RequestParam(name = "size", defaultValue = "5") Integer size,
             @RequestParam(name = "sort-by", defaultValue = "name") String sortBy,
             @RequestParam(name = "direction", defaultValue = "ASC") String direction
            ) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Customer> customerPage = customerService.pageCustomer(pageable);
        ControllerResponse<?> response = ControllerResponse.<PageResponWrapper<Customer>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message(ApiUrlConstans.GETDATA)
                .data(new PageResponWrapper<>(customerPage))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") String name) {
        Customer customer = customerService.getById(name);
        if (customer == null) {
            ControllerResponse<Customer> response = ControllerResponse.<Customer>builder()
                    .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(ApiUrlConstans.NODATA)
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        ControllerResponse<Customer> response = ControllerResponse.<Customer>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message(ApiUrlConstans.GETDATA)
                .data(customer)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "name") String name) {
        Customer customer = customerService.delete(name);

        if (customer == null) {
            ControllerResponse<Customer> response = ControllerResponse.<Customer>builder()
                    .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(ApiUrlConstans.NODATA)
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        ControllerResponse<Customer> response = ControllerResponse.<Customer>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message(ApiUrlConstans.DELLETE)
                .data(customer)
                .build();


        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Customer customer) {
        Customer customers = customerService.update(customer);
        ControllerResponse<Customer> response = ControllerResponse.<Customer>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message(ApiUrlConstans.UPDATE)
                .data(customers)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


}
