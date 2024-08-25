package com.scaler.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    //private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    //@OneToMany
    //private List<Product> featuredProducts;
}
