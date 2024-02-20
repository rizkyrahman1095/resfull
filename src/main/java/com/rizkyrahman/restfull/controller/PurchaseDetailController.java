package com.rizkyrahman.restfull.controller;


import com.rizkyrahman.restfull.dto.respon.ControllerResponse;
import com.rizkyrahman.restfull.entity.PurchaseDetail;
import com.rizkyrahman.restfull.service.PurchaseDetailService;
import com.rizkyrahman.restfull.util.constans.ApiUrlConstans;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUrlConstans.PURCHAS_EDETAIL)
public class PurchaseDetailController {


    public PurchaseDetailService purchaseDetailService;
    public PurchaseDetailController(PurchaseDetailService purchaseDetailService) {
        this.purchaseDetailService = purchaseDetailService;
    }


    @PostMapping
    public ResponseEntity<?> savePurchaseDetail(@RequestBody PurchaseDetail purchaseDetail){
        PurchaseDetail purchaseDetail1 = purchaseDetailService.savePurchaseDetail(purchaseDetail);
        ControllerResponse<PurchaseDetail> purchaseDetailControllerResponse = ControllerResponse.<PurchaseDetail>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message(ApiUrlConstans.CREATE)
                .data(purchaseDetail1)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseDetailControllerResponse);

    }
}
