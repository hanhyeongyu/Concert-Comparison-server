package kata.practice.concertcomparison.payment

import kata.practice.concertcomparison.application.PaymentApplication
import kata.practice.concertcomparison.application.command.AddPayment
import kata.practice.concertcomparison.application.command.ConfirmPayment
import kata.practice.concertcomparison.argumentResolver.UserId
import kata.practice.concertcomparison.common.exception.EntityNotFoundException
import kata.practice.concertcomparison.payment.PaymentsController.Companion.ENDPOINT
import kata.practice.concertcomparison.payment.viewmodel.PaymentViewModel
import kata.practice.concertcomparison.payment.query.GetPayment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping(ENDPOINT)
class PaymentsController(
    private val paymentApplication: PaymentApplication,
    private val paymentReadModel: PaymentReadModel
){

    @GetMapping
    private fun payment(@RequestParam paymentId: UUID): ResponseEntity<PaymentViewModel>{
        val query = GetPayment(paymentId)
        val result = paymentReadModel.payment(query)
        return ResponseEntity.ok(result)
    }

    @PostMapping()
    private fun add(
        @RequestBody request: PaymentRequests.AddPaymentRequest,
        @UserId userId: Long
    ): ResponseEntity<PaymentViewModel>{
        val paymentId = UUID.randomUUID()

        val command = AddPayment(
            userId = userId,
            paymentId = paymentId,
            amount = request.amount,
            currency = request.currency,
        )
        paymentApplication.handle(command)

        val query = GetPayment(paymentId)
        paymentReadModel.payment(query)
        val payment = paymentReadModel.payment(query) ?: throw EntityNotFoundException()
        return ResponseEntity.ok(payment)
    }

    @PostMapping("/confirm")
    private fun confirm(
        @RequestBody request: PaymentRequests.ConfirmPaymentRequest,
        @UserId userId: Long
    ): ResponseEntity<Void> {
        val command = ConfirmPayment(
            userId = userId,
            paymentId = request.paymentId,
            paymentKey = request.paymentKey,
            amount = request.amount,
            currency = request.currency,
        )
        paymentApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    companion object{
        internal const val ENDPOINT = "payments"
    }
}