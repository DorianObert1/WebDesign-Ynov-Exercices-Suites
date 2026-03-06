package com.ynov.exercice16.service;

import com.ynov.exercice16.model.Product;
import com.ynov.exercice16.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<Product> getById(Long id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public Mono<Product> create(Product product) {
        if (product.getName() == null || product.getName().isBlank()) {
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le nom est obligatoire"));
        }
        if (product.getPrice() == null || product.getPrice() < 0) {
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le prix ne peut pas être négatif"));
        }
        return productRepository.save(product);
    }

    public Flux<Product> getByMaxPrice(Double maxPrice) {
        if (maxPrice < 0) {
            throw new IllegalArgumentException("Le prix maximum ne peut pas être négatif");
        }
        return productRepository.findByPriceLessThanEqual(maxPrice);
    }
}
