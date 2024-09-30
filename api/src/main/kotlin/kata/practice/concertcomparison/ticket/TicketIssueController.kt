package kata.practice.concertcomparison.ticket

import kata.practice.concertcomparison.application.TicketApplication
import kata.practice.concertcomparison.application.command.IssueTickets
import kata.practice.concertcomparison.argumentResolver.UserId
import kata.practice.concertcomparison.ticket.TicketIssueController.Companion.ENDPOINT
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(ENDPOINT)
class TicketIssueController(
    private val ticketApplication: TicketApplication
){

    @PostMapping
    fun issue(
        @RequestBody request: TicketRequests.IssueTicketRequest,
        @UserId userId: Long,
    ){
        val issueTickets = IssueTickets(userId = userId, ticketIds = request.ticketIds)
        ticketApplication.handle(issueTickets)
    }

    companion object{
        internal const val ENDPOINT = "ticket/issue"
    }
}