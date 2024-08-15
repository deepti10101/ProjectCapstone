package com.scaler.productservice.services;

import com.scaler.productservice.configurations.RestTemplateConfiguration;
import com.scaler.productservice.dto.FakeStoreProductResponseDto;
import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class FakeStoreProductService implements ProductService{


    private RestTemplate restTemplate;

    @Autowired
    //preffered over field injection
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate= restTemplate;

    }
    @Override
    public Product getProductById(Long id) {
        FakeStoreProductResponseDto responseDto =restTemplate.getForObject(
                s"https://fakestoreapi.com/products/" + id,
                FakeStoreProductResponseDto.class);
        return responseDto.toProduct();

    }

    @Override
    public List<Product> getAllProducts() {
        //sList<FakeStoreProductResponseDto> responseDto= rest
        return List.of();
    }
}
