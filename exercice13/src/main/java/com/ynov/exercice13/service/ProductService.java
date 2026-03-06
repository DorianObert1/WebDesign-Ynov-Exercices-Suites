package com.ynov.exercice13.service;

import com.ynov.exercice13.model.Product;
import com.ynov.exercice13.repository.ProductRepository;
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

    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Mono<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Mono<Product> createProduct(Product product) {
        return productRepository.save(product);
    }

    public Mono<Product> updateProduct(Long id, Product product) {
        return productRepository.findById(id)
                .flatMap(existing -> {
                    existing.setName(product.getName());
                    existing.setPrice(product.getPrice());
                    existing.setStock(product.getStock());
                    return productRepository.save(existing);
                });
    }

    public Mono<Void> deleteProduct(Long id) {
        return productRepository.deleteById(id);
    }

    public Flux<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public Mono<Product> buyProduct(Long id, int quantity) {
        return productRepository.findById(id)
                .flatMap(product -> {
                    if (product.getStock() < quantity) {
                        return Mono.error(new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Stock insuffisant"));
                    }
                    product.setStock(product.getStock() - quantity);
                    return productRepository.save(product);
                });
    }
}
