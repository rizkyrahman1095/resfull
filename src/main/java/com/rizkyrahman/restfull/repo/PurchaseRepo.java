package com.rizkyrahman.restfull.repo;

import com.rizkyrahman.restfull.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepo extends JpaRepository<Purchase,String> {
}
