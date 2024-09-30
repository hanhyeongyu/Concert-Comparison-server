package kata.practice.concertcomparison.user.command

data class Register(
    val email: String,
    val encodedPassword: String
)