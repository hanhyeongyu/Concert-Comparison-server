package kata.practice.concertcomparison.payment

import java.util.UUID

class PaymentRequests {
    data class AddPaymentRequest(
        val amount: Long,
        val currency: String,
    )

    data class ConfirmPaymentRequest(
        val paymentId: UUID,
        val paymentKey: String,
        val amount: Long,
        val currency: String
    )
}