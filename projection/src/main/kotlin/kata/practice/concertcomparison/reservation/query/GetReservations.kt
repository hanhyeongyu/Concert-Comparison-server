package kata.practice.concertcomparison.reservation.query

data class GetReservations(
    val userId: Long,
    val continuationToken: String?
)