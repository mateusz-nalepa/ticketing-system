package com.nalepa.ticketing.order

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import de.codecentric.boot.admin.server.config.EnableAdminServer


@SpringBootApplication
@EnableAdminServer
class AdminServerApplication

fun main(args: Array<String>) {
	runApplication<AdminServerApplication>(*args)
}
