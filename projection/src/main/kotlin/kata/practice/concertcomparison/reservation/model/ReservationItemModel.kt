package kata.practice.concertcomparison.reservation.model

import kata.practice.concertcomparison.map.viewmodel.SeatViewModel
import kata.practice.concertcomparison.ticket.model.TicketViewModel
import java.util.UUID

data class ReservationItemModel(
    val reservationId: UUID,
    val ticket: TicketViewModel,
    val seat: SeatViewModel
)