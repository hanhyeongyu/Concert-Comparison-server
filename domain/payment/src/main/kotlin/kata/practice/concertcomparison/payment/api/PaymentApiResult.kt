package kata.practice.concertcomparison.payment.api

data class PaymentApiResult(
    val paymentKey: String,
    val orderId: String,
    val amount: Long,
)