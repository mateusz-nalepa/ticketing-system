package com.nalepa.ticketing.reporting.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ReportingEndpoint {

    @GetMapping("/dummy/{name}")
    fun dummyReporting(@PathVariable name: String): String {
        println("REPORTING: $name")
        return name
    }

}