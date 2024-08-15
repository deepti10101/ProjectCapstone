package com.scaler.productservice.services;

import com.scaler.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(Long id);
    public List<Product> getAllProducts();
}
