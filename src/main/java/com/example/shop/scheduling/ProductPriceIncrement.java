package com.example.shop.scheduling;

import com.example.shop.entity.Product;
import com.example.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(name = "app.scheduling.enabled")
public class ProductPriceIncrement {

    private final ProductRepository productRepository;

    public ProductPriceIncrement(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Scheduled(fixedDelayString = "${app.scheduling.period}")
    public void refreshPricingParameters() {
        List<Product> products = productRepository.findAll();
        products.forEach(product ->
                product.setPrice(product.getPrice()+(product.getPrice()*0.1)));
        productRepository.saveAll(products);

    }

}
