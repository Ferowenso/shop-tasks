package com.example.shop.dto;

import lombok.Data;

@Data
public class ProductFilter {

    private String name;
    private Integer count;
    private Double price;
    private Boolean isAvailable;
    private ComparisonOperator priceComparisonOperator;
    private ComparisonOperator countComparisonOperator;


}
