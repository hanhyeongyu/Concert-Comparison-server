package kata.practice.concertcomparison.application.command

data class IssueTickets(
    val userId: Long,
    val ticketIds: List<Long>
)