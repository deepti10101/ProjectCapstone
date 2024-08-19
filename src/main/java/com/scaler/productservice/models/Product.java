package com.scaler.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
        //private Long id;
        private String title;
        private String description;
        private Double price;
        private String imageUrl;
        @ManyToOne
        private Category category;
}
