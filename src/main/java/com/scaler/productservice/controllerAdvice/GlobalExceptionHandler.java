package com.scaler.productservice.controllerAdvice;

import com.scaler.productservice.dto.ErrorDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public  ResponseEntity<ErrorDto> handleProductDtoNotFoundException (ProductNotFoundException exception ){

        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus("FAILURE");
        errorDto.setMessage(exception.getMessage());
        ResponseEntity<ErrorDto> responseEntity= new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(404));
        return responseEntity;
    }

}
