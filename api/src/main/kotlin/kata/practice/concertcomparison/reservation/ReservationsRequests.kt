package kata.practice.concertcomparison.reservation

import java.util.UUID

class ReservationsRequests {

    data class AddReservations(
        val paymentKey: UUID,
        val performanceId: Long,
        val ticketIds: List<Long>
    )
}