package kata.practice.concertcomparison.ticket


import kata.practice.concertcomparison.ticket.TicketsController.Companion.ENDPOINT
import kata.practice.concertcomparison.ticket.model.TicketViewModel
import kata.practice.concertcomparison.ticket.query.GetTickets
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(ENDPOINT)
class TicketsController(
    private val ticketReadModel: TicketReadModel
){

    @GetMapping
    fun tickets(@RequestParam performanceId: Long): ResponseEntity<List<TicketViewModel>>{
        val getTickets = GetTickets(performanceId)
        val result = ticketReadModel.tickets(getTickets)
        return ResponseEntity.ok(result)
    }

    companion object{
        internal const val ENDPOINT = "tickets"
    }
}