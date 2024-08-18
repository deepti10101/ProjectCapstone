package com.scaler.productservice.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductRequestDto {
    private String title;
    private Double price;
    private String category;
    private String description;
    private String image;

    public void  toFakeStoreProductRequest(String title, String description, Double price, String imageUrl, String categoryName){
        this.setTitle(title);
        this.setDescription(description);
        this.setPrice(price);
        this.setImage(imageUrl);
        this.setCategory(categoryName);

    }
}
