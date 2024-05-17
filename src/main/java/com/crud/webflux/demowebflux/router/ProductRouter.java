package com.crud.webflux.demowebflux.router;

import com.crud.webflux.demowebflux.handler.ProductHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Slf4j
@Component
public class ProductRouter {
    private static final String PATH = "product";

    @Bean
    RouterFunction<ServerResponse> router(ProductHandler handler){
        return RouterFunctions.route()
                .GET(PATH, handler::getAll)
                .GET(PATH + "/{id}", handler::getById)
                .POST(PATH, handler::save)
                .DELETE(PATH + "/{id}", handler::delete)
                .PUT(PATH + "/{id}", handler::update)
                .build();
    }
}
