package com.rizkyrahman.restfull.service.implement;

import com.rizkyrahman.restfull.dto.respon.ControllerResponse;
import com.rizkyrahman.restfull.entity.Customer;
import com.rizkyrahman.restfull.entity.Product;
import com.rizkyrahman.restfull.entity.Purchase;
import com.rizkyrahman.restfull.entity.PurchaseDetail;
import com.rizkyrahman.restfull.repo.CustomerRepo;
import com.rizkyrahman.restfull.repo.ProductRepo;
import com.rizkyrahman.restfull.repo.PurchaseRepo;
import com.rizkyrahman.restfull.service.PurchaseDetailService;
import com.rizkyrahman.restfull.service.PurchaseService;
import com.rizkyrahman.restfull.util.constans.ApiUrlConstans;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepo purchaseRepo;

    private final CustomerRepo customerRepo;

    private final ProductRepo productRepo;
    private final PurchaseDetailService purchaseDetailService;

    public PurchaseServiceImpl(PurchaseRepo purchaseRepo, CustomerRepo customerRepo, ProductRepo productRepo, PurchaseDetailService purchaseDetailService) {
        this.purchaseRepo = purchaseRepo;
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
        this.purchaseDetailService = purchaseDetailService;
    }

    @Override
    @Transactional
    public ResponseEntity<?> savePurchase(Purchase purchase) {
        try {
            log.info("Request in {}", purchase);

            // validate customer
            Optional<Customer> customer = customerRepo.findById(purchase.getCustomer().getId());

            if (customer.isEmpty()) {
                ControllerResponse<Purchase> customerNotFound = ControllerResponse.<Purchase>builder()
                        .status(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .message(ApiUrlConstans.CUSTOMER_NOT_FOUND)
                        .data(null).build();

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerNotFound);
            }

            for (PurchaseDetail purchaseDetail : purchase.getPurchaseList()) {
                String productId = purchaseDetail.getProduct().getId();

                // validate product stock
                Optional<Product> product = productRepo.findById(productId);

                if (product.isEmpty()) {
                    ControllerResponse<Purchase> productFailed = ControllerResponse.<Purchase>builder()
                            .status(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .message(ApiUrlConstans.PRODUCTS_NOT_FOUND)
                            .data(null).build();

                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productFailed);
                }

                if (product.get().getStock() < purchaseDetail.getQuantity()) {
                    ControllerResponse<Purchase> productFailed = ControllerResponse.<Purchase>builder()
                            .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .message(ApiUrlConstans.PRODUCTS_NOT_FOUND)
                            .data(null).build();

                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productFailed);
                }

                purchaseDetailService.savePurchaseDetail(purchaseDetail);

                product.get().setStock(product.get().getStock() - purchaseDetail.getQuantity());
                productRepo.save(product.get());

                purchaseDetail.setProduct(product.get());
                purchaseDetail.setPriceSell(purchaseDetail.getPriceSell());
            }

            purchaseRepo.save(purchase);
            purchase.setCustomer(customer.get());
            ControllerResponse<Purchase> successPurchase = ControllerResponse.<Purchase>builder()
                    .status(HttpStatus.OK.getReasonPhrase())
                    .message(ApiUrlConstans.SUCCESS_PURCHASE)
                    .data(purchase).build();

            return ResponseEntity.status(HttpStatus.CREATED).body(successPurchase);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            ControllerResponse<Purchase> failedPurchase = ControllerResponse.<Purchase>builder()
                    .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(ApiUrlConstans.FAIL_PURCHASE)
                    .data(purchase).build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failedPurchase);
        }
    }
}
