package com.nalepa.ticketing.ticketing.domain

import com.nalepa.ticketing.ticketing.domain.docs.ReportingContext
import com.nalepa.ticketing.ticketing.domain.docs.ReportingConvention
import com.nalepa.ticketing.ticketing.domain.docs.ReportingDocumentation
import io.micrometer.observation.ObservationRegistry
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.function.Supplier

@Service
class DummyTicketUseCase(
    private val orderWebClient: WebClient,
    private val observationRegistry: ObservationRegistry,
) {

    private val convention = ReportingConvention()

    fun ticket(name: String, number: String): Mono<String> {
        return ReportingDocumentation
            .REPORT
            .observation(
                convention,
                convention,
                { ReportingContext(name, number) },
                observationRegistry,
            )
            .observe(Supplier { internalTicket(name, number) })!!
    }

    fun internalTicket(name: String, number: String): Mono<String> {
        return Mono
            .just(number.toLong())
            .delayElement(java.time.Duration.ofMillis(number.toLong()))
            .flatMap {
                orderWebClient
                    .get()
                    .uri("http://order-service/dummy/$name/$number")
                    .retrieve()
                    .bodyToMono(String::class.java)
            }
    }

}
