package com.scaler.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryName;

}
