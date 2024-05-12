package ru.netology.jdbc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.jdbc.repository.ProductRepository;

import java.util.List;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products/fetch-product")
    public List<String> getProductName(@RequestParam(value = "name", required = false) String name) {
        return productRepository.getProductName(name);
    }
}
