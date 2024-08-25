package com.scaler.productservice.services;

import com.scaler.productservice.controllerAdvice.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service("ProductDbService")
//@Primary
public class ProductDbService implements ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public ProductDbService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional=productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product not found with id="+id);
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String description, Double price, String imageUrl, String categoryName) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);

        product.setCategory(getCategoryFromDB(categoryName));
        return productRepository.save(product);


    }

    @Override
    public Product partialUpdate(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> productOptional=productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product not found with id="+id);
        }
        Product productFromDb=productOptional.get();
        if(productFromDb.getTitle()!=null){
            productFromDb.setTitle(product.getTitle());
        }
        if(productFromDb.getDescription()!=null){
            productFromDb.setDescription(product.getDescription());
        }
        if(productFromDb.getImageUrl()!=null){
            productFromDb.setImageUrl(product.getImageUrl());
        }
        if(productFromDb.getCategory()!=null){
            productFromDb.setCategory(getCategoryFromDB(product.getCategory().getName()));
        }
        productRepository.save(productFromDb);
        return productFromDb;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

    private Category getCategoryFromDB(String categoryName) {
        Optional<Category> categoryOption=categoryRepository.findByName(categoryName);
        if(categoryOption.isEmpty()){
            Category category = new Category();
            category.setName(categoryName);
            return categoryRepository.save(category);
        }
        return categoryOption.get();
    }
}
