package com.ynov.exercice16.repository;

import com.ynov.exercice16.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    Flux<Product> findByPriceLessThanEqual(Double maxPrice);
}
