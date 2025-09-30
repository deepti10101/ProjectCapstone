package com.scaler.productservice.services;

import com.scaler.productservice.controllerAdvice.ProductNotFoundException;
import com.scaler.productservice.dto.FakeStoreProductResponseDto;
import com.scaler.productservice.models.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class FakeStoreProductServiceTest {


    private RestTemplate restTemplate= Mockito.mock(RestTemplate.class);


    private FakeStoreProductService fakeStoreProductService=
            new FakeStoreProductService(restTemplate);

    @Test
    public void testGetProductByIdThenReturnCorrectProduct() throws ProductNotFoundException {
        FakeStoreProductResponseDto dummyResponse =
                new FakeStoreProductResponseDto();
        dummyResponse.setId(1L);
        dummyResponse.setTitle("abc");
        dummyResponse.setDescription("abcd");
        dummyResponse.setPrice("100.0");
        dummyResponse.setCategory("electronics");
        dummyResponse.setImage("img/url");

        when(restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+1L,FakeStoreProductResponseDto.class)).thenReturn(dummyResponse);
        Product response =fakeStoreProductService.getProductById(1L);
        assertEquals(1L,  response.getId());
        assertEquals("abc",  response.getTitle());
    }

    /*@Test
    public void testGetProductByIdThenThrowsProductNotFoundException() throws ProductNotFoundException {
        when(restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+1L,
                FakeStoreProductResponseDto.class)).thenReturn(null);
        Product response =fakeStoreProductService.getProductById(1L);
        assertThrows(ProductNotFoundException.class,()->
                );


    }*/

   // @Test
   /* public void testCreatProduct(){

    }*/
}