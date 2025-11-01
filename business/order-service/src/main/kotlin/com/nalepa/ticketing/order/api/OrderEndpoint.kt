package com.nalepa.ticketing.order.api

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient

@RestController
class OrderEndpoint(
    private val reportingServiceRestClient: RestClient,
) {

    @GetMapping("/dummy/{name}")
    fun dummyOrder(@PathVariable name: String): String {
        println("ORDER: $name")
        return reportingServiceRestClient
            .get()
            .uri("http://reporting-service/dummy/$name")
            .retrieve()
            .body(String::class.java)!!
    }

}

@Configuration
class ReportingServiceClientConfiguration {


    @Bean
    @LoadBalanced
    fun restClientBuilder(): RestClient.Builder {
        return RestClient.builder();
    }

    @Bean
    fun reportingServiceRestClient(restClientBuilder: RestClient.Builder): RestClient {
        return restClientBuilder.build()
    }

}