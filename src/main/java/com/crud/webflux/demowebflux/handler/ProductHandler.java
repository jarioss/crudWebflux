package com.crud.webflux.demowebflux.handler;

import com.crud.webflux.demowebflux.dto.ProductoDTO;
import com.crud.webflux.demowebflux.entity.Product;
import com.crud.webflux.demowebflux.service.ProductService;
import com.crud.webflux.demowebflux.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductHandler {

    private final ProductService productService;
    private final ObjectValidator validator;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        Flux<Product> products = productService.getAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(products, Product.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        Mono<Product> product = productService.getById(id);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(product, Product.class);
    }

    public Mono<ServerResponse> save(ServerRequest request){
        Mono<ProductoDTO> dtoMono = request.bodyToMono(ProductoDTO.class).doOnNext(validator::validate);
        return dtoMono.flatMap(p -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.save(p), Product.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.delete(id), Void.class);
    }

    public Mono<ServerResponse> update(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        Mono<ProductoDTO> product = request.bodyToMono(ProductoDTO.class).doOnNext(validator::validate);
        return product.flatMap(p -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.update(id, p), Product.class));
    }

}
