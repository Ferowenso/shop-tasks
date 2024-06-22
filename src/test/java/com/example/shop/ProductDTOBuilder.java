package com.example.shop;

import com.example.shop.dto.ProductDTO;
import com.example.shop.entity.Categories;
import lombok.Builder;

import static com.example.shop.entity.Categories.FRUIT;

public class ProductDTOBuilder {

    public static final String DEFAULT_NAME = "Apple";
    public static final String DEFAULT_DESCRIPTION = "Green";
    public static final Categories DEFAULT_CATEGORY = FRUIT;
    public static final Double DEFAULT_PRICE = 20D;
    public static final Integer DEFAULT_COUNT = 10;
    public static final Boolean DEFAULT_IS_AVAILABLE = true;


    private String name = DEFAULT_NAME;
    private String description = DEFAULT_DESCRIPTION;
    private Categories category = DEFAULT_CATEGORY;
    private Double price = DEFAULT_PRICE;
    private Integer count = DEFAULT_COUNT;
    private Boolean isAvailable = DEFAULT_IS_AVAILABLE;

    public static ProductDTOBuilder productDTO(){
        return new ProductDTOBuilder();
    }

    public ProductDTOBuilder withName(String name){
        this.name = name;
        return this;
    }

    public ProductDTOBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public ProductDTOBuilder withCategory(Categories category){
        this.category = category;
        return this;
    }

    public ProductDTOBuilder withPrice(Double price){
        this.price = price;
        return this;
    }

    public ProductDTOBuilder withCount(Integer count){
        this.count = count;
        return this;
    }

    public ProductDTOBuilder withIsAvailable(Boolean isAvailable){
        this.isAvailable = isAvailable;
        return this;
    }

    public ProductDTO build(){
        return ProductDTO.builder()
                .name(name)
                .count(count)
                .category(category)
                .price(price)
                .description(description)
                .isAvailable(isAvailable)
                .build();
    }

}
