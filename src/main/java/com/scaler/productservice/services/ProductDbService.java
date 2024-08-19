package com.scaler.productservice.services;

import com.scaler.productservice.controllerAdvice.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductDbService")
//@Primary
public class ProductDbService implements ProductService {

    ProductRepository productRepository;

    @Autowired
    public ProductDbService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(String title, String description, Double price, String imageUrl, String categoryName) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        productRepository.save(product);

        return product;
    }

    @Override
    public Product partialUpdate(Long id, Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }
}
