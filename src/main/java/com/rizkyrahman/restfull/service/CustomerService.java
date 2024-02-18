package com.rizkyrahman.restfull.service;

import com.rizkyrahman.restfull.entity.Customer;
import com.rizkyrahman.restfull.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
        Customer create(Customer customer);
        List<Customer> getAll();
        Customer getById(String id);
        Customer update(Customer customer);
        Customer delete(String id);
        Page<Customer> pageCustomer(Pageable pageable);
}
