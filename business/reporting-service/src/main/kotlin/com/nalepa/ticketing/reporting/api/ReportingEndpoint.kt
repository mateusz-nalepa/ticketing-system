package com.nalepa.ticketing.reporting.api

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ReportingEndpoint {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)


    @GetMapping("/dummy/{name}")
    fun dummyReporting(@PathVariable name: String): String {
        logger.info("REPORTING: $name")
        return name
    }

}