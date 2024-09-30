package kata.practice.concertcomparison.payment

import kata.practice.concertcomparison.payment.viewmodel.PaymentViewModel
import kata.practice.concertcomparison.payment.query.GetPayment

interface PaymentReadModel {
    fun payment(getPayment: GetPayment): PaymentViewModel?
}