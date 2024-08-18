package com.scaler.productservice.controllerAdvice;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException(String message){
        super(message);
    }
}
