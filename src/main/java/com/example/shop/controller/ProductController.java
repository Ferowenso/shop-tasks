package com.example.shop.controller;

import com.example.shop.dto.ProductDTO;
import com.example.shop.dto.ProductFilter;
import com.example.shop.entity.Product;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;


    @GetMapping("{uuid}")
    public ResponseEntity<Product> getProduct(@PathVariable UUID uuid){
        return new ResponseEntity<>(productService.getProductById(uuid), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UUID> createProduct(@RequestBody ProductDTO productDTO){
            return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @PutMapping("{uuid}")
    public ResponseEntity<UUID> updateProduct(@PathVariable UUID uuid,@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.updateProduct(uuid, productDTO), HttpStatus.OK);
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity deleteProduct(@PathVariable UUID uuid){
        productService.deleteProductById(uuid);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("search")
    public ResponseEntity<List<Product>> searchProducts(@RequestBody ProductFilter productFilter){
        return new ResponseEntity<>(productService.searchProducts(productFilter), HttpStatus.OK);
    }

}
