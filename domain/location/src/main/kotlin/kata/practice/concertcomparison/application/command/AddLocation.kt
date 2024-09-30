package kata.practice.concertcomparison.application.command

data class AddLocation(
    val name: String,
    val city: String,
    val street: String,
    val zipCode: String
)