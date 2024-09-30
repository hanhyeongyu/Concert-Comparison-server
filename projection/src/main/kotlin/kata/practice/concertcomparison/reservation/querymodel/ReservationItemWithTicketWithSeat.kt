package kata.practice.concertcomparison.reservation.querymodel

import kata.practice.concertcomparison.map.viewmodel.SeatViewModel
import kata.practice.concertcomparison.model.Seat
import kata.practice.concertcomparison.model.reservationItem.ReservationItem
import kata.practice.concertcomparison.reservation.model.ReservationItemModel
import kata.practice.concertcomparison.model.Ticket
import kata.practice.concertcomparison.ticket.model.TicketViewModel

data class ReservationItemWithTicketWithSeat(
    val reservationItem: ReservationItem,
    val ticket: Ticket,
    val seat: Seat
){
    fun toModel(): ReservationItemModel{
        return ReservationItemModel(
            reservationId = reservationItem.reservationId,
            ticket = TicketViewModel(ticket),
            seat = SeatViewModel(seat)
        )
    }
}