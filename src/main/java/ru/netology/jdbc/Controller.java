package ru.netology.jdbc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    Repository repository = new Repository();

    @GetMapping("/products/fetch-product")
    public List<String> getProductName(@RequestParam(value = "name", required = false) String name) {
        return repository.getProductName(name);
    }
}
