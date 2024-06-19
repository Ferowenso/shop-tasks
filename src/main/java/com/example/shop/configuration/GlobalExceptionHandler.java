package com.example.shop.configuration;

import com.example.shop.dto.ErrorDTO;
import com.example.shop.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> productNotFoundException(ProductNotFoundException ex){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setClassname(ex.getClass().getName());
        errorDTO.setMessage(ex.getMessage());
        errorDTO.setTimestamp(new Date().toString());
        errorDTO.setStatus(String.valueOf(ex.getStatus().value()));

        return new ResponseEntity<>(errorDTO, ex.getStatus());
    }

}
