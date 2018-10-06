package com.yunjae.reactivetestservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;

@Configuration
public class RouterFunctionConfig {
    @Bean
    RouterFunction<ServerResponse> greetings() {
        RouterFunction<ServerResponse> route = RouterFunctions
                .route(RequestPredicates.GET("/fnc/hi"),
                        serverRequest -> ServerResponse.ok().body(Flux.just("Hello, world!"), String.class));
        return route;
    }
}
