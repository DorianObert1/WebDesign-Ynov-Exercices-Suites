package com.ynov.exercice16.controller;

import com.ynov.exercice16.model.Product;
import com.ynov.exercice16.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final ProductService productService;

    public InventoryController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Mono<Product> getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> create(@RequestBody Product product) {
        return productService.create(product);
    }

    @GetMapping("/price/{maxPrice}")
    public Flux<Product> getByMaxPrice(@PathVariable Double maxPrice) {
        return productService.getByMaxPrice(maxPrice);
    }
}
