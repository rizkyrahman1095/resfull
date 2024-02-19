package com.rizkyrahman.restfull.service.implement;

import com.rizkyrahman.restfull.entity.Product;
import com.rizkyrahman.restfull.entity.Purchase;
import com.rizkyrahman.restfull.entity.PurchaseDetail;
import com.rizkyrahman.restfull.repo.PurchaseRepo;
import com.rizkyrahman.restfull.service.ProductService;
import com.rizkyrahman.restfull.service.PurchaseDetailService;
import com.rizkyrahman.restfull.service.PurchaseService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;



@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepo purchaseRepo;
    private final ProductService productService;
    private final PurchaseDetailService purchaseDetailService;


    public PurchaseServiceImpl(PurchaseRepo purchaseRepo, ProductService productService, PurchaseDetailService purchaseDetailService) {
        this.purchaseRepo = purchaseRepo;
        this.productService = productService;
        this.purchaseDetailService = purchaseDetailService;
    }

    @Override
    @Transactional
    public Purchase savePurchase(Purchase purchase) {
        Purchase purchase1 = purchaseRepo.save(purchase);
        for (PurchaseDetail purchaseDetail : purchase.getPurchaseList()) {
                purchaseDetail.setPurchase(purchase1);
            purchaseDetailService.savePurchaseDetail(purchaseDetail);
        }
        return purchase1;
    }
}