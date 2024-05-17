package com.crud.webflux.demowebflux.repository;

import com.crud.webflux.demowebflux.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
}
