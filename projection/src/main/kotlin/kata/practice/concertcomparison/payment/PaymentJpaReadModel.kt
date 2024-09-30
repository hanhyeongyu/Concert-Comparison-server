package kata.practice.concertcomparison.payment

import kata.practice.concertcomparison.payment.query.GetPayment
import kata.practice.concertcomparison.payment.viewmodel.PaymentViewModel
import kata.practice.concertcomparison.repository.PaymentRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull


@Service
class PaymentJpaReadModel(
    private val paymentRepository: PaymentRepository
) : PaymentReadModel{
    override fun payment(getPayment: GetPayment): PaymentViewModel? {
        val payment = paymentRepository.findByPaymentId(getPayment.paymentId)
            .getOrNull() ?: return null
        return PaymentViewModel(payment)
    }
}