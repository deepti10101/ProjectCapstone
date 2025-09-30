package com.scaler.productservice.controllers;

import com.scaler.productservice.common.AuthenticationCommons;
import com.scaler.productservice.controllerAdvice.ProductNotFoundException;
import com.scaler.productservice.dto.ProductResponseDto;
import com.scaler.productservice.dto.Role;
import com.scaler.productservice.dto.UserDto;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @MockBean
    @Qualifier("FakeStoreProductService")
    private ProductService productService;

    @MockBean
    private AuthenticationCommons authenticationCommons;

    @Autowired
    private ProductController productController;

    @Test
    public void testGetProductByIdReturnsProduct() throws ProductNotFoundException {
        Product dummyProduct = new Product();
        dummyProduct.setId(1L);
        dummyProduct.setTitle("abc");
        dummyProduct.setDescription("abcd");
        dummyProduct.setPrice(100.0);
        dummyProduct.setImageUrl("img/url");

        Category category = new Category();
        category.setId(1L);
        category.setName("electronics");
        dummyProduct.setCategory(category);
        when(productService.getProductById(1L)).thenReturn(dummyProduct);

        String dummyToken="dummyToken";
        UserDto dummyUserDto= new UserDto();
        dummyUserDto.setEmail("dummy@email.com");
        List<Role> dummyRoles= new ArrayList<>();
        dummyRoles.add(new Role("Mentor"));
        dummyUserDto.setRoles(dummyRoles);
        dummyUserDto.setName("Deepthi");
        when(authenticationCommons.validateToken(dummyToken)).thenReturn(dummyUserDto);

       ProductResponseDto responseDto =
                productController
                        .getProductById(1L,dummyToken);

       assertEquals(1L,responseDto.getId());
    }

    @Test
    public void testGetProductByIdProductIsNull() throws ProductNotFoundException {

        String dummyToken="dummyToken";
        UserDto dummyUserDto= new UserDto();
        dummyUserDto.setEmail("dummy@email.com");
        List<Role> dummyRoles= new ArrayList<>();
        dummyRoles.add(new Role("Mentor"));
        dummyUserDto.setRoles(dummyRoles);
        dummyUserDto.setName("Deepthi");
        when(authenticationCommons.validateToken(dummyToken)).thenReturn(dummyUserDto);

        when(productService.getProductById(1L)).thenReturn(null);


        ProductResponseDto responseDto=productController.getProductById(1L,dummyToken);
        assertNull(responseDto);
    }



}