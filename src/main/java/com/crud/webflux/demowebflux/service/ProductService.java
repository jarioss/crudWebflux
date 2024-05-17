package com.crud.webflux.demowebflux.service;

import com.crud.webflux.demowebflux.entity.Product;
import com.crud.webflux.demowebflux.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Flux<Product> getAll() {
        return productRepository.findAll();
    }

    public Mono<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    public Mono<Void> delete(Long id) {
        return productRepository.deleteById(id);
    }

    public Mono<Product> update(Long id, Product product) {
        return productRepository.findById(id)
                .flatMap(p -> {
                    p.setName(product.getName());
                    p.setPrice(product.getPrice());
                    return productRepository.save(p);
                });
    }


}
