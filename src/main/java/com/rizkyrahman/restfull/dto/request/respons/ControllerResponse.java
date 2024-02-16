package com.rizkyrahman.restfull.dto.request.respons;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControllerResponse<T> {
    private String status;
    private String message;
    private T data;
}
