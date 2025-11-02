package com.nalepa.ticketing.order.api

import com.nalepa.ticketing.order.domain.DummyOrderUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient

@RestController
class DummyOrderEndpoint(
    private val dummyOrderUseCase: DummyOrderUseCase,
) {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/dummy/{name}/{number}")
    fun dummyOrderEndpoint(@PathVariable name: String, @PathVariable number: String): String {
        logger.info("ORDER: name: $name, number: $number")

        return dummyOrderUseCase.order(name, number)
    }

}

