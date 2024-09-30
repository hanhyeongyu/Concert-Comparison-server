package kata.practice.concertcomparison.application.command

import java.util.UUID

data class ConfirmPayment(
    val userId: Long,
    val paymentId: UUID,
    val paymentKey: String,
    val amount: Long,
    val currency: String
)