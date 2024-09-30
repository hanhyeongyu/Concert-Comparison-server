package kata.practice.concertcomparison.ticket

class TicketRequests {
    data class SelectTicketRequest(
        val ticketIds: List<Long>
    )

    data class IssueTicketRequest(
        val ticketIds: List<Long>
    )
}