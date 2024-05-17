package com.crud.webflux.demowebflux.controller;

import com.crud.webflux.demowebflux.entity.Product;
import com.crud.webflux.demowebflux.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Flux<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Product> getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    public Mono<Product> save(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return productService.delete(id);
    }

    @PutMapping("/{id}")
    public Mono<Product> update(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }

}
