package com.rizkyrahman.restfull.service;

import com.rizkyrahman.restfull.entity.Purchase;
import org.springframework.http.ResponseEntity;

public interface PurchaseService {
    ResponseEntity<?> savePurchase(Purchase purchase);
}
