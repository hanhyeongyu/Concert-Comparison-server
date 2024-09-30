package kata.practice.concertcomparison.payment


import jakarta.persistence.*
import kata.practice.concertcomparison.BaseEntity
import kata.practice.concertcomparison.payment.Payment.Status.*
import java.util.*

@Entity
class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val userId: Long,

    @Column(unique = true)
    val paymentId: UUID,

    var paymentKey: String? = null,

    val amount: Long,

    val currency: String,

    @Enumerated(EnumType.STRING)
    var status: Status
): BaseEntity(){


    fun cancel(){
        if (status == CONFIRM){
            throw IllegalStateException()
        }
        status = CANCEL
    }


    fun confirm(
        userId: Long,
        paymentId: UUID,
        paymentKey: String,
        amount: Long,
    ){
        if (!confirmValidate(userId, paymentId, amount)){
            throw  IllegalArgumentException()
        }

        this.paymentKey = paymentKey
        this.status = CONFIRM
    }

    private fun confirmValidate(
        userId: Long,
        paymentId: UUID,
        amount: Long,
    ): Boolean{
        return this.userId == userId &&
                this.paymentId == paymentId &&
                this.amount == amount
    }

    fun fail(){
        this.status = FAIL
    }


    enum class Status{
        REQUEST,
        CONFIRM,
        FAIL,
        CANCEL,
    }
}