package kata.practice.concertcomparison.ticket

import kata.practice.concertcomparison.repository.TicketRepository
import kata.practice.concertcomparison.ticket.model.TicketViewModel
import kata.practice.concertcomparison.ticket.query.GetTickets
import org.springframework.stereotype.Service

@Service
class TicketJpaReadModel(
    private val ticketRepository: TicketRepository
): TicketReadModel{
    override fun tickets(getTickets: GetTickets): List<TicketViewModel> {
        return ticketRepository.findAllByPerformanceId(getTickets.performanceId)
            .map(::TicketViewModel)
    }
}