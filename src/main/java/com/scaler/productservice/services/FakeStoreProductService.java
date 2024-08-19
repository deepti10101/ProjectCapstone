package com.scaler.productservice.services;

import com.scaler.productservice.configurations.RestTemplateConfiguration;
import com.scaler.productservice.controllerAdvice.ProductNotFoundException;
import com.scaler.productservice.dto.FakeStoreProductRequestDto;
import com.scaler.productservice.dto.FakeStoreProductResponseDto;
import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{


    private RestTemplate restTemplate;

    @Autowired
    //preffered over field injection
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate= restTemplate;

    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        FakeStoreProductResponseDto responseDto =restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductResponseDto.class);
        if(responseDto==null){
            throw new ProductNotFoundException("Product not found with id="+id);
        }
        return responseDto.toProduct();

    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductResponseDto[] responseDto= restTemplate.getForObject(
                "https://fakestoreapi.com/products/" ,
                FakeStoreProductResponseDto[].class);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductResponseDto responseDto1 : responseDto) {
            products.add(responseDto1.toProduct());
        }

        return products;
    }

    @Override
    public Product createProduct(String title, String description, Double price, String imageUrl, String categoryName) {
        FakeStoreProductRequestDto requestDto = new FakeStoreProductRequestDto();
        requestDto.toFakeStoreProductRequest(title, description, price, imageUrl, categoryName);

        ResponseEntity<FakeStoreProductResponseDto> responseDto= restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                requestDto,
                FakeStoreProductResponseDto.class);
        return responseDto.getBody().toProduct();

    }

    @Override
    public Product partialUpdate(Long id, Product product) {
        HttpEntity<Product> httpEntity =
                new HttpEntity<>(product); // Add dto object here

        ResponseEntity<FakeStoreProductResponseDto> responseEntity =
                restTemplate.exchange(
                        "https://fakestoreapi.com/products" + id,
                        HttpMethod.PATCH,
                        httpEntity, // use dto here
                        FakeStoreProductResponseDto.class
                );

        FakeStoreProductResponseDto responseDto = responseEntity.getBody();

        return responseDto.toProduct();
    }

    @Override
    public Product deleteProduct(Long id) {
        //<Long> httpEntity = new HttpEntity<>(id);
        ResponseEntity<FakeStoreProductResponseDto> responseEntity =
                    restTemplate.exchange(
                            "https://fakestoreapi.com/products/"+id,
                            HttpMethod.DELETE,
                            null,
                            FakeStoreProductResponseDto.class
                            );
        FakeStoreProductResponseDto response=responseEntity.getBody();

        return response.toProduct();
    }

}
