package com.rizkyrahman.restfull.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductSearchDto {
    private String productName;
    private Integer productStock;
    private Integer productPrice;

}
