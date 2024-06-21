package com.example.shop.controller;

import com.example.shop.ProductBuilder;
import com.example.shop.ProductDTOBuilder;
import com.example.shop.dto.ProductDTO;
import com.example.shop.entity.Product;
import com.example.shop.exception.ProductNotFoundException;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.ProductService;
import io.restassured.RestAssured;
import jakarta.annotation.PostConstruct;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @LocalServerPort
    private int port;

    private final static String BASE_URI = "http://localhost";

    @MockBean
    private ProductService productService;

    @Before
    public void configureRestAssured() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }

    @Test
    public void when_create_product_then_status_201() {

        ProductDTO productDTO = ProductDTOBuilder.productDTO().build();

        given().port(port)
                .contentType("application/json")
                .body(productDTO)
                .when().post("/api/products")

                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void when_get_product_then_status_200() {

        UUID uuid = UUID.randomUUID();

        when(productService.getProductById(any(UUID.class))).thenReturn(Product.builder().id(uuid).build());

        given().port(port)
                .contentType("application/json")
                .when().get("/api/products/{id}", uuid.toString())
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void when_get_product_then_status_400(){
        given().port(port)
                .contentType("application/json")
                .when().get("/api/products/{id}","12345678910")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_get_product_then_status_404(){

        when(productService.getProductById(any(UUID.class))).thenThrow(ProductNotFoundException.class);

        given().port(port)
                .contentType("application/json")
                .when().get("/api/products/{id}",UUID.randomUUID().toString())
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void when_get_all_products_then_status_200(){
        given().port(port)
                .contentType("application/json")
                .when().get("/api/products")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void when_delete_product_then_status_200(){
        given().port(port)
                .contentType("application/json")
                .when().delete("/api/products/{id}", UUID.randomUUID().toString())
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void when_delete_product_then_status_400(){
        given().port(port)
                .contentType("application/json")
                .when().delete("/api/products/{id}", "12345678910")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_delete_product_then_status_404(){

        doThrow(ProductNotFoundException.class).when(productService).deleteProductById(any(UUID.class));

        given().port(port)
                .contentType("application/json")
                .when().delete("/api/products/{id}", UUID.randomUUID().toString())
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void when_update_product_then_status_404(){
        ProductDTO productDTO = ProductDTOBuilder.productDTO().build();
        when(productService.updateProduct(any(UUID.class), any(ProductDTO.class))).thenThrow(ProductNotFoundException.class);

        given().port(port)
                .contentType("application/json")
                .body(productDTO)
                .when().put("/api/products/{id}", UUID.randomUUID().toString())
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void when_update_product_then_status_200(){
        ProductDTO productDTO = ProductDTOBuilder.productDTO().build();
        when(productService.updateProduct(any(UUID.class), any(ProductDTO.class))).thenReturn(UUID.randomUUID());

        given().port(port)
                .contentType("application/json")
                .body(productDTO)
                .when().put("/api/products/{id}", UUID.randomUUID().toString())
                .then()
                .statusCode(HttpStatus.OK.value());
    }

}
