package kata.practice.concertcomparison.ticket

import kata.practice.concertcomparison.ticket.model.TicketViewModel
import kata.practice.concertcomparison.ticket.query.GetTickets

interface TicketReadModel {
    fun tickets(getTickets: GetTickets): List<TicketViewModel>
}