package com.example.shop;

import com.example.shop.dto.ProductDTO;
import com.example.shop.entity.Categories;
import com.example.shop.entity.Product;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.shop.entity.Categories.FRUIT;

public class ProductBuilder {

    public static final UUID DEFAULT_UUID = UUID.randomUUID();
    public static final String DEFAULT_NAME = "Apple";
    public static final String DEFAULT_DESCRIPTION = "Green";
    public static final Categories DEFAULT_CATEGORY = FRUIT;
    public static final Double DEFAULT_PRICE = 20D;
    public static final Integer DEFAULT_COUNT = 10;
    private static final LocalDateTime DEFAULT_CREATED_AT = LocalDateTime.now();
    private static final LocalDateTime DEFAULT_LAST_CHANGE_COUNT = LocalDateTime.now();
    public static final Boolean DEFAULT_IS_AVAILABLE = true;


    private UUID uuid = DEFAULT_UUID;
    private String name = DEFAULT_NAME;
    private String description = DEFAULT_DESCRIPTION;
    private Categories category = DEFAULT_CATEGORY;
    private Double price = DEFAULT_PRICE;
    private Integer count = DEFAULT_COUNT;
    private LocalDateTime createdAt = DEFAULT_CREATED_AT;
    private LocalDateTime lastChangeCount = DEFAULT_LAST_CHANGE_COUNT;
    private Boolean isAvailable = DEFAULT_IS_AVAILABLE;


    public static ProductBuilder product(){
        return new ProductBuilder();
    }

    public ProductBuilder withUUID(UUID uuid){
        this.uuid = uuid;
        return this;
    }
    public ProductBuilder withName(String name){
        this.name = name;
        return this;
    }

    public ProductBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public ProductBuilder withCategory(Categories category){
        this.category = category;
        return this;
    }

    public ProductBuilder withPrice(Double price){
        this.price = price;
        return this;
    }

    public ProductBuilder withCount(Integer count){
        this.count = count;
        return this;
    }

    public ProductBuilder withCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
        return this;
    }

    public ProductBuilder withLastChangeCount(LocalDateTime lastChangeCount){
        this.lastChangeCount = lastChangeCount;
        return this;
    }

    public ProductBuilder withIsAvailable(Boolean isAvailable){
        this.isAvailable = isAvailable;
        return this;
    }

    public Product build(){
        return Product.builder()
                .id(uuid)
                .name(name)
                .count(count)
                .category(category)
                .price(price)
                .description(description)
                .createdAt(createdAt)
                .lastCountChange(lastChangeCount)
                .isAvailable(isAvailable)
                .build();
    }


}
