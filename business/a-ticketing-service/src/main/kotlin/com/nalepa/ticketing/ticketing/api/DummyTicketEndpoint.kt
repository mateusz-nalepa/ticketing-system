package com.nalepa.ticketing.ticketing.api

import com.nalepa.ticketing.ticketing.domain.DummyTicketUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class DummyTicketEndpoint(
    private val dummyTicketUseCase: DummyTicketUseCase,
) {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/dummy/{name}/{number}")
    fun dummyTicketEndpoint(@PathVariable name: String, @PathVariable number: String): Mono<String> {
        logger.info("TICKETING: name: $name, number: $number")
        return dummyTicketUseCase.ticket(name, number)
    }

}
