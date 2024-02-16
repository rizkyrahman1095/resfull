package com.rizkyrahman.restfull.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store_product")
public class ProductEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;
        private String name;
        private Integer price;
        private Integer stock;


    }
