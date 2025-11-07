package com.nalepa.ticketing.order.api

import com.nalepa.ticketing.order.domain.ConcertOrderRepository
import com.nalepa.ticketing.order.infra.database.ConcertOrderEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FindAllConcertsEndpoint(
    private val concertOrderRepository: ConcertOrderRepository,
) {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/concerts")
    fun findAll(): List<ConcertDto> {
        logger.info("API - findAll concerts")

        return concertOrderRepository
            .findAll()
            .map { ConcertDto(it.concertId, it.name) }
    }

}

data class ConcertDto(
    val concertId: String,
    val concertName: String,
)