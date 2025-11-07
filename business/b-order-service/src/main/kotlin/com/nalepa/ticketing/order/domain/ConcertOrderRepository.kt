package com.nalepa.ticketing.order.domain

import com.nalepa.ticketing.order.infra.database.ConcertOrderEntity

interface ConcertOrderRepository {
    fun findAll(): List<ConcertOrderEntity>
    fun getConcert(id: String): ConcertOrderEntity?
    fun updateConcert(concertOrderEntity: ConcertOrderEntity): ConcertOrderEntity
}


data class Concert(
    val concertId: String,
    val seats: List<String>,
    val venueType: VenueType,
)

enum class VenueType {
    STADIUM,
    CLUB
    ;
}