package com.yunjae.reactivetestservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class PublisherController {
    @GetMapping("/hi")
    Flux<String> hi() {
       return Flux.just("Hi");
    }
}
