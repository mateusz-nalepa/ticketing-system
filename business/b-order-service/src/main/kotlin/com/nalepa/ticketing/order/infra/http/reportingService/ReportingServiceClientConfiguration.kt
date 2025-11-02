package com.nalepa.ticketing.order.infra.http.reportingService

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

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