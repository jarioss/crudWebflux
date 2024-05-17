package com.crud.webflux.demowebflux.service;

import com.crud.webflux.demowebflux.entity.Product;
import com.crud.webflux.demowebflux.exception.CustomException;
import com.crud.webflux.demowebflux.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private static final String PRODUCT_NOT_FOUND = "Product not found";
    private static final String PRODUCT_ALREADY_EXISTS = "Product already exists";

    private final ProductRepository productRepository;

    public Flux<Product> getAll() {
        return productRepository.findAll();
    }

    public Mono<Product> getById(Long id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")));
    }

    public Mono<Product> save(Product product) {
        Mono<Boolean> existsName = productRepository.findByName(product.getName()).hasElement();
        return existsName.flatMap(exists -> exists
                ? Mono.error(new CustomException( HttpStatus.BAD_REQUEST, PRODUCT_ALREADY_EXISTS))
                : productRepository.save(product));
    }

    public Mono<Void> delete(Long id) {
        Mono<Boolean> exists = productRepository.findById(id).hasElement();
        return exists.flatMap(exist -> exist
                ? productRepository.deleteById(id)
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND, PRODUCT_NOT_FOUND)));
    }

    public Mono<Product> update(Long id, Product product) {
        Mono<Boolean> productExists = productRepository.findById(id).hasElement();
        Mono<Boolean> productRepeatedName = productRepository.repeateName(id, product.getName()).hasElement();
        return productExists.flatMap(exists -> exists
                ? productRepeatedName.flatMap(repeated -> repeated
                ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST, PRODUCT_ALREADY_EXISTS))
                : productRepository.save(new Product(id, product.getName(), product.getPrice())))
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND, PRODUCT_NOT_FOUND)));
    }
}
