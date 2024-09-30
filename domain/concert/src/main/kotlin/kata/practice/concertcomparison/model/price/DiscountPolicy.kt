package kata.practice.concertcomparison.model.price

import jakarta.persistence.*
import kata.practice.concertcomparison.BaseEntity
import kata.practice.concertcomparison.common.exception.IllegalStatusException


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class DiscountPolicy(
    open var concertId: Long,
    open var name: String
): BaseEntity(){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null

    abstract fun discount(money: Long): Long
}


@Entity
class AmountDiscountPolicy(
    concertId: Long,
    name: String,
    var amount: Long,
): DiscountPolicy(concertId, name) {
    override fun discount(money: Long): Long {
        return amount
    }
}

@Entity
class PercentDiscountPolicy(
    concertId: Long,
    name: String,
    percent: Int,
): DiscountPolicy(concertId, name) {

    var percent: Int = percent
        private set

    init {
        isSatisfyPercent()
    }

    fun updatePercent(percent: Int) {
        isSatisfyPercent()
        this.percent = percent
    }

    override fun discount(money: Long): Long {
        return (money / 100) * percent
    }

    private fun isSatisfyPercent() {
        if (percent < 0 || percent > 100) {
            throw IllegalStatusException("percent must be between 0 and 100")
        }
    }
}