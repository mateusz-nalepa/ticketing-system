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
    fun buyTicket(@RequestBody buyTicketRequest: BuyTicketRequest): Mono<BuyTicketResponse> {
        // TODO: validate 4 tickets per user

        // TODO: Tickets cannot be sold once the concert is sold out.
        // to raczej po stronie order-service ta walidacja

        // TODO: zawołaj odpowiedni endpoint w order-service

        return orderWebClient
            .get()
            .uri("http://order-service/api/orders")
            .retrieve()
            .bodyToMono(BuyTicketResponse::class.java)
    }

}

data class BuyTicketRequest(
    val concertId: String,
    val seatIds: List<String>,
)

data class BuyTicketResponse(
    val ticketIds: String,
)

