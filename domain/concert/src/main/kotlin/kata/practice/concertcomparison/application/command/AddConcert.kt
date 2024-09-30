package kata.practice.concertcomparison.application.command

data class AddConcert(
    val name: String,
    val description: String,
    val price: Long
)