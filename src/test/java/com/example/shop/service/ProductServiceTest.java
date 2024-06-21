package com.example.shop.service;

import com.example.shop.ProductBuilder;
import com.example.shop.ProductDTOBuilder;
import com.example.shop.dto.ProductDTO;
import com.example.shop.entity.Categories;
import com.example.shop.entity.Product;
import com.example.shop.exception.ProductNotFoundException;
import com.example.shop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {


    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


    @Test
    void when_save_product_it_should_return_product_uuid() {

        ProductDTO productDTO = ProductDTOBuilder.productDTO().build();

        UUID expected = UUID.randomUUID();

        Product product = ProductBuilder.product().build();

        when(productRepository.save(any(Product.class))).thenReturn(product);

        UUID productCreatedUUID = productService.createProduct(productDTO);

        assertEquals(expected, productCreatedUUID);
    }

    @Test
    void get_product_by_id_when_product_does_not_exist(){
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(UUID.randomUUID()), "Expected ProductNotFoundException");

    }

    @Test
    void get_product_by_id_it_should_return_product(){
        UUID expected = UUID.randomUUID();

        Product product = ProductBuilder.product().build();

        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.of(product));

        Product foundProduct = productService.getProductById(expected);

        assertNotNull(foundProduct, "The returned product should not be null");
        assertEquals(expected, foundProduct.getId(), "The returned product ID should match the expected ID");
        verify(productRepository, times(1)).findById(expected);
    }

    @Test
    void delete_product_by_id_when_product_does_not_exist(){
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.deleteProductById(UUID.randomUUID()), "Expected ProductNotFoundException");
        verify(productRepository, never()).delete(any(Product.class));
    }
    @Test
    void delete_product_by_id_when_product_exist(){
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.deleteProductById(UUID.randomUUID()), "Expected ProductNotFoundException");

    }

    @Test
    void update_product_should_return_updated_product(){
        ProductDTO productDTO = ProductDTO.builder()
                .name("Apple")
                .price(10D)
                .category(Categories.FRUIT)
                .count(10)
                .build();

        UUID exceptedUUID = UUID.randomUUID();

        Product product = Product.builder()
                .id(exceptedUUID)
                .name("Peach")
                .price(20D)
                .category(Categories.FRUIT)
                .count(10)
                .build();

        when(productRepository.findById(exceptedUUID)).thenReturn(Optional.of(product));

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        when(productRepository.save(productCaptor.capture())).thenReturn(product);

        UUID updatedProductId = productService.updateProduct(exceptedUUID, productDTO);
        assertEquals(exceptedUUID, updatedProductId);

        Product capturedProductEntity = productCaptor.getValue();
        assertEquals(productDTO.getName(), capturedProductEntity.getName());
        assertEquals(productDTO.getDescription(), capturedProductEntity.getDescription());
        assertEquals(productDTO.getCategory(), capturedProductEntity.getCategory());
        assertEquals(productDTO.getPrice(), capturedProductEntity.getPrice());
        assertEquals(productDTO.getCount(), capturedProductEntity.getCount());
    }

    @Test
    void update_product_when_product_does_not_exist(){
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(UUID.randomUUID(), ProductDTO.builder().build()), "Expected ProductNotFoundException");
    }

    @Test
    void update_product_chanhe_count_should_return_updated_product(){
        Integer oldCount = 10;
        Integer newCount = 15;

        ProductDTO productDTO = ProductDTOBuilder.productDTO().withCount(newCount).build();

        Product product = ProductBuilder.product().withCount(oldCount).build();

        LocalDateTime oldTime = product.getLastCountChange();

        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        productService.updateProduct(product.getId(), productDTO);

        assertNotNull(product.getLastCountChange());
        assertNotEquals(oldTime, product.getLastCountChange());

    }

}
