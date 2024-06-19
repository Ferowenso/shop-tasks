package com.example.shop.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message){
        super(message);
    }

    public HttpStatus getStatus(){
        return HttpStatus.NOT_FOUND;
    }

}
