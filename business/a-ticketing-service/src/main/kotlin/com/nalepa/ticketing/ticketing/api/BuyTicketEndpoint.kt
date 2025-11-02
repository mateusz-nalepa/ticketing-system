package com.nalepa.ticketing.ticketing.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@RestController
class BuyTicketEndpoint(
    private val orderWebClient: WebClient,
) {

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

