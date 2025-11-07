package com.nalepa.ticketing.order.infra.database

import com.nalepa.ticketing.order.domain.ConcertOrderRepository
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class PostgresConcertOrderRepository(
    private val springPostgresOrderRepository: SpringPostgresOrderRepository,
) : ConcertOrderRepository {
    override fun findAll(): List<ConcertOrderEntity> {
        return springPostgresOrderRepository.findAll()
    }

    override fun getConcert(id: String): ConcertOrderEntity? {
        return springPostgresOrderRepository
            .findById(id)
            .getOrNull()
    }

    override fun updateConcert(concertOrderEntity: ConcertOrderEntity): ConcertOrderEntity {
        return springPostgresOrderRepository.save(concertOrderEntity)
    }

}

interface SpringPostgresOrderRepository : JpaRepository<ConcertOrderEntity, String>
//
//@Component
//class PostgresStarter(
//    private val springPostgresOrderRepository: SpringPostgresOrderRepository,
//) {
//
////    @PostConstruct
////    fun initPostgres() {
////        springPostgresOrderRepository
////            .findById("test")
////            .getOrNull()
////            ?: run {
////                OrderEntity("test").let { springPostgresOrderRepository.save(it) }
////            }
////    }
//}


//@Entity(name = "orders")
//class OrderEntity(
//    @Id
//    val id: String
//)


fun <T> Optional<T>.getOrNull(): T? =
    if (this.isPresent) {
        this.get()
    } else {
        null
    }


@Entity(name = "concerts")
data class ConcertOrderEntity(
    @Id
    @Column(name = "concert_id")
    val concertId: String,

    @Column(name = "name")
    val name: String,

    @OneToMany(mappedBy = "concert", cascade = [CascadeType.ALL], orphanRemoval = true)
    @Column(name = "seats")
    val seats: List<SeatEntity> = emptyList(),

    @Enumerated(value = EnumType.STRING)
    val venueType: VenueTypeEntity
)

@Entity(name = "concert_seats")
data class SeatEntity(
    @Id
    val seatId: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id")
    val concert: ConcertOrderEntity,

    var userId: String? = null,
)

enum class VenueTypeEntity {
    STADIUM,
    CLUB
    ;
}