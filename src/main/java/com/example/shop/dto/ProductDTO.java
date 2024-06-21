package com.example.shop.dto;

import com.example.shop.entity.Categories;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDTO {

    private String name;

    private String description;

    private Double price;

    private Integer count;

    private Categories category;

}
