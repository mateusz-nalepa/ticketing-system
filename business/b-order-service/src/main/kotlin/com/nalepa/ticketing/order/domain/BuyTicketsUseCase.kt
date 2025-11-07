package com.nalepa.ticketing.order.domain


// TODO - endpoints
data class BuyTicketRequest(
    val concertId: String,
    val seatIds: List<String>,
)

data class BuyTicketResponse(
    val ticketIds: String,
)


//TODO - zapis do bazy danych
//
//TODO - w transakcji zapis do bazy i do osobnej kolekcji OrderPlaced
//a jak error, to OrderRejected
class BuyTicketsUseCase {
}