package com.rizkyrahman.restfull.service.implement;

import com.rizkyrahman.restfull.entity.Customer;
import com.rizkyrahman.restfull.repo.CustomerRepo;
import com.rizkyrahman.restfull.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer create(Customer customer) {

        return customerRepo.save(customer);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getById(String id) {
        Optional<Customer> customer = customerRepo.findById(id);
        return customer.orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Customer delete(String id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            customerRepo.deleteById(id);
            return customer.get();
        }
        return null;
    }

    @Override
    public Page<Customer> pageCustomer(Pageable pageable) {
        return customerRepo.findAll(pageable);
    }
}
