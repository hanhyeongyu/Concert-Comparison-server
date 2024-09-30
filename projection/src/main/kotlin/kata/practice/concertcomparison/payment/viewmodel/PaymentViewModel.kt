package kata.practice.concertcomparison.payment.viewmodel

import kata.practice.concertcomparison.payment.Payment
import java.util.UUID


data class PaymentViewModel(
    val paymentId: UUID,
    val userId: Long,
    val paymentKey: String?,
    val amount: Long,
    val currency: String,
    val status: Status
){
    enum class Status{
        ADDED,
        REQUEST,
        CONFIRM,
        FAIL,
        CANCEL,
    }

    constructor(payment: Payment): this(
        paymentId = payment.paymentId,
        userId = payment.userId,
        paymentKey = payment.paymentKey,
        amount = payment.amount,
        currency = payment.currency,
        status = when(payment.status){
            Payment.Status.REQUEST -> Status.REQUEST
            Payment.Status.CONFIRM -> Status.CONFIRM
            Payment.Status.FAIL -> Status.FAIL
            Payment.Status.CANCEL -> Status.CANCEL
        }
    )
}
