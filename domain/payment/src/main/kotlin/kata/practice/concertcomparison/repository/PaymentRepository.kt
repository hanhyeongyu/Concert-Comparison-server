package kata.practice.concertcomparison.repository

import kata.practice.concertcomparison.payment.Payment
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PaymentRepository: JpaRepository<Payment, Long>{
    fun findByPaymentId(paymentId: UUID): Optional<Payment>
}