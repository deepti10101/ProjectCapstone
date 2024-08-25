package com.scaler.productservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ProductResponseEntity {
    ProductResponseDto responseDto;
    private HttpStatus status;
    private String message;
}
