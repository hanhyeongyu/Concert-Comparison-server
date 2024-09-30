package kata.practice.concertcomparison.payment.api

import org.springframework.stereotype.Service
import java.util.UUID


@Service
class PaymentApi {

    fun confirm(
        paymentKey: String,
        orderId: String,
        amount: Long,
    ): PaymentApiResult {
        return PaymentApiResult(
            paymentKey = UUID.randomUUID().toString(),
            orderId = orderId,
            amount = amount
        )
    }
}