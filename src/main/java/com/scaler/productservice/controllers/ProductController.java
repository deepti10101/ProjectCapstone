
package com.scaler.productservice.controllers;


import com.scaler.productservice.controllerAdvice.ProductNotFoundException;
import com.scaler.productservice.dto.ErrorDto;
import com.scaler.productservice.dto.ProductRequestDto;
import com.scaler.productservice.dto.ProductResponseDto;
import com.scaler.productservice.dto.ProductResponseEntity;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {


    ProductService productService;

    @Autowired
    public ProductController(@Qualifier("ProductDbService") ProductService productService, RestTemplate restTemplate) {
      this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public ProductResponseDto getProductById(@PathVariable("id") Long id)
            throws ProductNotFoundException
             {
        Product product = productService.getProductById(id);
        return ProductResponseDto.from(product);
    }

    @GetMapping("/product")
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        List<ProductResponseDto> responseDtos = new ArrayList<>();
        for(Product product: products) {
            responseDtos.add(ProductResponseDto.from(product));
        }

        return responseDtos;
    }

    @PostMapping("/product")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.createProduct(
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getPrice(),
                productRequestDto.getImageUrl(),
                productRequestDto.getCategoryName()
        );

        return ProductResponseDto.from(product);
    }

    @DeleteMapping("/Product/{id}")
    public ProductResponseDto deleteProduct(@PathVariable Long id) {
        Product product =productService.deleteProduct(id);
        return ProductResponseDto.from(product);

    }

    @PatchMapping("/product/{id}")
    public ProductResponseEntity partialUpdateProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDto productRequestDto) {
        ProductResponseEntity productResponseEntity= new ProductResponseEntity();
        Product productResponse = null;
        try {
            productResponse = productService.partialUpdate(id, ProductRequestDto.toProduct(productRequestDto));
        } catch (ProductNotFoundException e) {
            productResponseEntity.setMessage("Error"+ e.getMessage());
            productResponseEntity.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

            throw new RuntimeException(e);
        }
        productResponseEntity.setResponseDto(ProductResponseDto.from(productResponse));
        productResponseEntity.setMessage("Successfully updated Product");
        productResponseEntity.setStatus(HttpStatus.OK);
        return productResponseEntity;

    }

    /*@ExceptionHandler(NullPointerException.class)
    public ErrorDto nullPointerrExceptionHandler(){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("someting went wrong ");
        errorDto.setStatus("FAILURE");

        return errorDto;
    }*/
}
