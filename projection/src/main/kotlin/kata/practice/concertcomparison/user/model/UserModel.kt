package kata.practice.concertcomparison.user.model

import kata.practice.concertcomparison.user.User

data class UserModel(
    val id: Long,
    val email: String,
    val encodedPassword: String
)


fun User.model(): UserModel {
    return UserModel(
        id = this.id!!,
        email = this.email.value,
        encodedPassword = this.encodedPassword.value
    )
}