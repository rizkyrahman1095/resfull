package com.rizkyrahman.restfull.service.implement;

import com.rizkyrahman.restfull.entity.Purchase;
import com.rizkyrahman.restfull.entity.PurchaseDetail;
import com.rizkyrahman.restfull.repo.PurchaseDetailRepo;
import com.rizkyrahman.restfull.service.PurchaseDetailService;
import com.rizkyrahman.restfull.service.PurchaseService;
import org.springframework.stereotype.Service;


@Service
public class PurchaseDetailServiceImpl implements PurchaseDetailService {

    private final PurchaseDetailRepo purchaseDetailRepo;

    public PurchaseDetailServiceImpl(PurchaseDetailRepo purchaseDetailRepo) {
        this.purchaseDetailRepo = purchaseDetailRepo;
    }
    @Override
    public PurchaseDetail savePurchaseDetail(PurchaseDetail purchaseDetail) {
        return purchaseDetailRepo.save(purchaseDetail);
    }
}
