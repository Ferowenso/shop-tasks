package com.example.shop.service;

import com.example.shop.aop.LogExecutionTime;
import com.example.shop.dto.ProductDTO;
import com.example.shop.entity.Product;
import com.example.shop.exception.ProductNotFoundException;
import com.example.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    @LogExecutionTime
    public UUID createProduct(ProductDTO productDTO){
        Product product = Product.builder()
                .name(productDTO.getName())
                .count(productDTO.getCount())
                .price(productDTO.getPrice())
                .category(productDTO.getCategory())
                .description(productDTO.getDescription())
                        .build();
        return productRepository.save(product).getId();

    }

    @LogExecutionTime
    public Product getProductById(UUID id){
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Not Found"));
    }

    @LogExecutionTime
    public void deleteProductById(UUID id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Not Found"));
        productRepository.delete(product);

    }

    @LogExecutionTime
    public UUID updateProduct(UUID uuid, ProductDTO productDTO){
        Product product = productRepository.findById(uuid).orElseThrow(() -> new ProductNotFoundException("Not Found"));

        product.setCategory(productDTO.getCategory());
        if (!Objects.equals(product.getCount(), productDTO.getCount())){
            product.setCount(productDTO.getCount());
            product.setLastCountChange(LocalDateTime.now());
        }

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());

        return productRepository.save(product).getId();
    }

    @LogExecutionTime
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
