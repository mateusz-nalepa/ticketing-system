package com.nalepa.ticketing.ticketing.infra.http.orderService

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class OrderServiceClientConfiguration {

    @Bean
    @LoadBalanced
    fun webClientBuilder(): WebClient.Builder {
        return WebClient.builder();
    }

    @Bean
    fun orderWebClient(webClientBuilder: WebClient.Builder): WebClient {
        return webClientBuilder.build()
    }

}