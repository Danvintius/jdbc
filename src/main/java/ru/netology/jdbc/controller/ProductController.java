package ru.netology.jdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.netology.jdbc.repository.ProductRepository;

import java.util.List;

@Controller
public class ProductController {
    ProductRepository repository = new ProductRepository();

    @GetMapping("/products/fetch-product")
    public List<String> getProductName(@RequestParam(value = "name", required = false) String name) {
        return repository.getProductName(name);
    }
}
