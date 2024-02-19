package com.rizkyrahman.restfull.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "t_purchase_detail")
public class PurchaseDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Integer quantity;
    private Integer priceSell;

    @ManyToOne
    @JsonIgnoreProperties("purchaseList")
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}
