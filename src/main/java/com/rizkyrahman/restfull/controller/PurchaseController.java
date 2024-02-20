package com.rizkyrahman.restfull.controller;


import com.rizkyrahman.restfull.entity.Purchase;
import com.rizkyrahman.restfull.service.implement.PurchaseServiceImpl;
import com.rizkyrahman.restfull.util.constans.ApiUrlConstans;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUrlConstans.PURCHASE)
public class PurchaseController {
    private PurchaseServiceImpl purchaseService;

    public PurchaseController(PurchaseServiceImpl purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<?> createPurchase(@RequestBody Purchase purchase){
         return purchaseService.savePurchase(purchase);

    }
}
