package com.scaler.productservice.dto;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import javax.xml.catalog.Catalog;

@Getter
@Setter
public class ProductRequestDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryName;

    public static Product toProduct(ProductRequestDto productRequestDto){
        Product product= new Product();
        product.setTitle(productRequestDto.getTitle());
        product.setDescription(productRequestDto.getDescription());
        product.setImageUrl(productRequestDto.getImageUrl());
        product.setPrice(productRequestDto.getPrice());
        Category category= new Category();
        category.setName(productRequestDto.getCategoryName());
        product.setCategory(category);
       return product;
    }

}
