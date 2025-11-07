package com.nalepa.ticketing.order.infra.database

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class InitOrderDatabase(
    private val springPostgresOrderRepository: SpringPostgresOrderRepository,
) {

    @PostConstruct
    fun initOrderDatabase() {
        springPostgresOrderRepository.deleteAll()

        val firstConcert =
            ConcertOrderEntity(
                concertId = "concert1",
                name = "Chopin",
                seats = emptyList(),
                venueType = VenueTypeEntity.CLUB,
            )

        val firstConcertWithSeats =
            firstConcert.copy(
                seats = listOf(
                    SeatEntity("seat1", firstConcert),
                    SeatEntity("seat2", firstConcert),
                    SeatEntity("seat3", firstConcert),
                    SeatEntity("seat4", firstConcert),
                    SeatEntity("seat5", firstConcert),
                    SeatEntity("seat6", firstConcert),
                    SeatEntity("seat7", firstConcert),
                    SeatEntity("seat8", firstConcert),
                    SeatEntity("seat9", firstConcert),
                    SeatEntity("seat10", firstConcert),
                )
            )

        val secondConcert =
            ConcertOrderEntity(
                concertId = "concert2",
                name = "Mozart",
                seats = emptyList(),
                venueType = VenueTypeEntity.STADIUM,
            )

        val secondConcertWithSeats =
            secondConcert.copy(
                seats = listOf(
                    SeatEntity("seat1", firstConcert),
                    SeatEntity("seat2", firstConcert),
                    SeatEntity("seat3", firstConcert),
                    SeatEntity("seat4", firstConcert),
                    SeatEntity("seat5", firstConcert),
                    SeatEntity("seat6", firstConcert),
                    SeatEntity("seat7", firstConcert),
                    SeatEntity("seat8", firstConcert),
                    SeatEntity("seat9", firstConcert),
                    SeatEntity("seat10", firstConcert),
                )
            )

        springPostgresOrderRepository.save(firstConcertWithSeats)
        springPostgresOrderRepository.save(secondConcertWithSeats)
    }

}