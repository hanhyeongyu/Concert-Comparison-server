package kata.practice.concertcomparison.ticket.model

import kata.practice.concertcomparison.model.Ticket


data class TicketViewModel(
    var id: Long,
    var performanceId: Long,
    val mapId: Long,
    val seatId: Long,
    val enable: Boolean
){
    constructor(ticket: Ticket) : this(
        id = ticket.id!!,
        performanceId = ticket.performanceId,
        mapId = ticket.mapId,
        seatId = ticket.seatId,
        enable = ticket.enable()
    )
}