package com.example.shop.dto;

import lombok.Data;

@Data
public class ErrorDTO {

    private String status;
    private String message;
    private String timestamp;
    private String classname;

}
