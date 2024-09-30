package kata.practice.concertcomparison.reservation.query

import java.util.UUID

data class GetReservationItems(
    val reservationId: UUID
)