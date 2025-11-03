package com.nalepa.ticketing.ticketing.infra.http.orderService

import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class OrderServiceClientConfiguration {


    // in this approach, passing tracing headers does not work, dunno XD
//    @Bean
//    @LoadBalanced
//    fun webClientBuilder(): WebClient.Builder {
//        return WebClient.builder();
//    }

    @Bean
    @LoadBalanced
    fun webClientBuilder(customizers: ObjectProvider<WebClientCustomizer>): WebClient.Builder {
        val builder = WebClient.builder()
        customizers.orderedStream().forEach { it.customize(builder) }
        return builder
    }

    @Bean
    fun orderWebClient(webClientBuilder: WebClient.Builder): WebClient {
        return webClientBuilder.build()
    }

}