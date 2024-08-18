package com.scaler.productservice.controllerAdvice;

import com.scaler.productservice.dto.ErrorDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ErrorDto nullPointerrExceptionHandler(){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("someting went wrong-1   ");
        errorDto.setStatus("FAILURE");

        return errorDto;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDto handleProductDtoNotFoundException (ProductNotFoundException exception ){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus("FAILURE");
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }

}
