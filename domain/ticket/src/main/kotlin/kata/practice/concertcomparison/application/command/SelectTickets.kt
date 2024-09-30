package kata.practice.concertcomparison.application.command

data class SelectTickets(
    val userId: Long,
    val ticketIds: List<Long>
)