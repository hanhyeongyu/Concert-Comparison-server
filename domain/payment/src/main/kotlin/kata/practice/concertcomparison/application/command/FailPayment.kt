package kata.practice.concertcomparison.application.command

import java.util.UUID

data class FailPayment(
    val paymentId: UUID
)