package kata.practice.concertcomparison.application.command

import java.util.UUID

data class AddPayment(
    val userId: Long,
    val paymentId: UUID,
    val amount: Long,
    val currency: String
)