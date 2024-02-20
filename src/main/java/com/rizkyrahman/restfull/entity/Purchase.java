package com.rizkyrahman.restfull.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name = "t_purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
//    @JsonIgnoreProperties("purchaseList")
    @OneToMany(mappedBy = "purchase")
    private List<PurchaseDetail> purchaseList = new ArrayList<>();

}
