package com.nalepa.ticketing.order.infra

import jakarta.annotation.PostConstruct
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class PostgresOrderRepository(
    private val springPostgresOrderRepository: SpringPostgresOrderRepository,
) {

}

interface SpringPostgresOrderRepository : JpaRepository<OrderEntity, String>

@Component
class PostgresStarter(
    private val springPostgresOrderRepository: SpringPostgresOrderRepository,
) {

    @PostConstruct
    fun initPostgres() {
        springPostgresOrderRepository
            .findById("test")
            .getOrNull()
            ?: run {
                OrderEntity("test").let { springPostgresOrderRepository.save(it) }
            }
    }
}


@Entity(name = "orders")
class OrderEntity(
    @Id
    val id: String
)


fun <T> Optional<T>.getOrNull(): T? =
    if (this.isPresent) {
        this.get()
    } else {
        null
    }