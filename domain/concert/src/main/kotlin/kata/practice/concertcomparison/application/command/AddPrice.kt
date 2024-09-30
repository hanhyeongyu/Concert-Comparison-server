package kata.practice.concertcomparison.application.command

data class AddPrice(
    val concertId: Long,
    val price: Long
)