package com.nalepa.ticketing.order.infra.http.reportingService

import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.web.client.RestClientCustomizer
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class ReportingServiceClientConfiguration {


    // in this approach, passing tracing headers does not work, dunno XD
//    @Bean
//    @LoadBalanced
//    fun restClientBuilder(): RestClient.Builder {
//        return RestClient.builder();
//    }

    @Bean
    @LoadBalanced
    fun restClientBuilder(
        customizers: ObjectProvider<RestClientCustomizer>
    ): RestClient.Builder {
        val builder = RestClient.builder()
        customizers.orderedStream().forEach { it.customize(builder) }
        return builder
    }

    @Bean
    fun reportingServiceRestClient(restClientBuilder: RestClient.Builder): RestClient {
        return restClientBuilder.build()
    }

}