package com.nalepa.ticketing.ticketing

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@RestController
class BuyTicketEndpoint(
    private val orderWebClient: WebClient,
) {

    @GetMapping("/dummy/{name}")
    fun buyTicket(@PathVariable name: String): Mono<String> {
        println("TICKETING: $name")
        return orderWebClient
            .get()
            .uri("http://order-service/dummy/$name")
            .retrieve()
            .bodyToMono(String::class.java)
    }

    @PostMapping("/tickets/buy")
    fun buyTicket(@RequestBody buyTicketRequest: BuyTicketRequest): Mono<String> {
        return orderWebClient
            .get()
            .uri("http://order-service/api/orders")
            .retrieve()
            .bodyToMono(String::class.java)
    }

}

data class BuyTicketRequest(
    val concertIds: List<String>,
    val ticketId: String,
)

@Configuration
class OrderServiceClientConfiguration {


    @Bean
    @LoadBalanced
    fun webClientBuilder(): WebClient.Builder {
        return WebClient.builder();
    }

    @Bean
    fun orderWebClient(webClientBuilder: WebClient.Builder): WebClient {
        return webClientBuilder.build()
    }

}