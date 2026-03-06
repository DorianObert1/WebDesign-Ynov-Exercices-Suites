package com.ynov.exercice12.repository;

import com.ynov.exercice12.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
    Flux<Order> findByStatus(String status);
    Flux<Order> findByCustomerName(String customerName);
    Flux<Order> findAllBy(Pageable pageable);
}
