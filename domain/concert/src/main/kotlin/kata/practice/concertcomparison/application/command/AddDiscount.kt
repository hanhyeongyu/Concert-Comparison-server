package kata.practice.concertcomparison.application.command

data class AddDiscountPolicy(
    val concertId: Long,
    val name: String,
    val percent: Int
)