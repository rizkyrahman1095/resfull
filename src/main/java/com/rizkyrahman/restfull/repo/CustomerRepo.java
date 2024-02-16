package com.rizkyrahman.restfull.repo;

import com.rizkyrahman.restfull.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,String> {
}
