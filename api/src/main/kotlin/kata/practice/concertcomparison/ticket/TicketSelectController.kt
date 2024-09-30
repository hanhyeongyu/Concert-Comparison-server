package kata.practice.concertcomparison.ticket

import kata.practice.concertcomparison.application.TicketApplication
import kata.practice.concertcomparison.application.command.SelectTickets
import kata.practice.concertcomparison.argumentResolver.UserId
import kata.practice.concertcomparison.ticket.TicketSelectController.Companion.ENDPOINT
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(ENDPOINT)
class TicketSelectController(
    private val ticketApplication: TicketApplication
){

    @PostMapping
    fun select(
        @RequestBody request: TicketRequests.SelectTicketRequest,
        @UserId userId: Long
    ): ResponseEntity<Void>{
        val ticketIds = request.ticketIds
        val selectTickets = SelectTickets(userId = userId, ticketIds = ticketIds)
        ticketApplication.handle(selectTickets)
        return ResponseEntity.ok().build()
    }

    companion object{
        internal const val ENDPOINT = "ticket/select"
    }
}