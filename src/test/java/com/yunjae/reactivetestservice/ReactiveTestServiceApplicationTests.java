package com.yunjae.reactivetestservice;

import com.yunjae.reactivetestservice.services.PublisherService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactiveTestServiceApplicationTests {

    @Autowired
    private PublisherService publisherService;

    private WebTestClient webTestClient;

    @Autowired
    private ApplicationContext context;

    @Before
    public void before() throws Exception {
        this.webTestClient = WebTestClient
                .bindToApplicationContext(context)
                .configureClient()
                .baseUrl("http://localhost:8080/")
                .build();
    }

    @Test
    public void getGreeting() throws Exception {
        webTestClient
                .get()
                .uri("/fnc/hi")
                .exchange()
                .expectStatus().isOk()
                ;
    }

    @Test
    public void contextLoads() {
        StepVerifier
                .withVirtualTime(() -> publisherService.publish()
                        .take(10)
                        .collectList())
                .thenAwait(Duration.ofHours(10))
                .consumeNextWith(list -> Assert.assertEquals(list.size(), 10))
                .verifyComplete();

    }

}
