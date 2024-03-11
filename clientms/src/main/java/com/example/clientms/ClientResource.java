package com.example.clientms;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ClientResource {

    @Autowired
    private WebClient webClient;

    /**
     * We'll call userms from here
     */
    @GetMapping("/client-users")
    @CircuitBreaker(name = "usermsclient", fallbackMethod = "usermsfallback")
    public Mono getUsersFromUserms() {
        return webClient.get().uri("/users").retrieve().bodyToMono(Object.class);
    }

    private Mono usermsfallback(CallNotPermittedException ex) {
        // fetch from cache, or call a backupms
        return Mono.just("CallNotPermittedException happened");
    }

    private Mono usermsfallback(Exception ex) {
        return Mono.just("Exception happened");
    }
}
