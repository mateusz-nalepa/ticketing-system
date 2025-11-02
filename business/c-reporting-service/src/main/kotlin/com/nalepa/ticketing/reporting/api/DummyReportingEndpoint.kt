package com.nalepa.ticketing.reporting.api

import com.nalepa.ticketing.reporting.domain.DummyReportUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class DummyReportingEndpoint(
    private val dummyReportUseCase: DummyReportUseCase,
) {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/dummy/{name}/{number}")
    fun dummyReporting(@PathVariable name: String, @PathVariable number: String): String {
        logger.info("REPORTING: name: $name, number: $number")

        return dummyReportUseCase.report(name, number)
    }

}
