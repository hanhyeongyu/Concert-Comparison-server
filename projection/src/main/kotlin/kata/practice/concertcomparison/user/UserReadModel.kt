package kata.practice.concertcomparison.user

import kata.practice.concertcomparison.user.model.UserModel
import kata.practice.concertcomparison.user.query.GetUserByEmail

interface UserReadModel {
    fun user(getUserByEmail: GetUserByEmail): UserModel?
}