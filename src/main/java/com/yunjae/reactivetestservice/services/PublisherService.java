package com.yunjae.reactivetestservice.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;

@Service
public class PublisherService {
    public Flux<String> publish() {
        return Flux
                .<String>generate(sink -> sink.next("Hello @ " + Instant.now().toString()))
                .delayElements(Duration.ofSeconds(1));
    }
}
