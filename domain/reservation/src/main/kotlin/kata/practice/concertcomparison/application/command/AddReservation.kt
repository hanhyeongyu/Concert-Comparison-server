package kata.practice.concertcomparison.application.command

import java.util.UUID

data class AddReservation(
    val reservationId: UUID,

    val userId: Long,

    val paymentId: UUID,

    val performanceId: Long,

    val ticketIds: List<Long>
)
