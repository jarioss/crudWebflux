package com.crud.webflux.demowebflux.repository;

import com.crud.webflux.demowebflux.entity.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    Mono<Product> findByName(String name);

    @Query("SELECT * FROM product WHERE id != :id AND name = :name")
    Mono<Product> repeateName(Long id, String name);
}
