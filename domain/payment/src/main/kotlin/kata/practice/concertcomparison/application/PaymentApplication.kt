package kata.practice.concertcomparison.application

import kata.practice.concertcomparison.application.command.AddPayment
import kata.practice.concertcomparison.application.command.ConfirmPayment
import kata.practice.concertcomparison.application.command.FailPayment
import kata.practice.concertcomparison.common.exception.EntityNotFoundException
import kata.practice.concertcomparison.payment.Payment
import kata.practice.concertcomparison.payment.Payment.Status.REQUEST
import kata.practice.concertcomparison.payment.api.PaymentApi
import kata.practice.concertcomparison.repository.PaymentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull


/**
 *     1. Serer  -> 결제 요청 정보 저장 (orderId, amount)
 *     2. Client -> 결제 요청(인증)  (paymentKey)
 *     3. Server -> 결제 요청 검증 후 승인 요청
 *     4. Server -> 승인 요청 정보 저장
 *
 */

@Service
class PaymentApplication(
    private val paymentRepository: PaymentRepository,
    private val paymentApi: PaymentApi
){

    @Transactional
    fun handle(command: AddPayment){
        val newPayment = Payment(
            userId = command.userId,
            paymentId = command.paymentId,
            amount = command.amount,
            currency = command.currency,
            status = REQUEST
        )
        paymentRepository.save(newPayment)
    }

    @Transactional
    fun handle(command: ConfirmPayment){
        val payment = paymentRepository.findByPaymentId(command.paymentId)
            .getOrNull() ?: throw EntityNotFoundException()

        payment.confirm(
            userId = command.userId,
            paymentKey = command.paymentKey,
            paymentId = payment.paymentId,
            amount = payment.amount
        )

        paymentApi.confirm(
            paymentKey = command.paymentKey,
            orderId = payment.paymentId.toString(),
            amount = payment.amount
        )

        paymentRepository.save(payment)
    }

    @Transactional
    fun handle(command: FailPayment){
        val payment = paymentRepository.findByPaymentId(command.paymentId)
            .getOrNull() ?: throw EntityNotFoundException()
        payment.fail()
        paymentRepository.save(payment)
    }

}