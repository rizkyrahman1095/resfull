package com.rizkyrahman.restfull.dto.respon;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ControllerResponse<T> {
    private String status;
    private String message;
    private T data;
}
