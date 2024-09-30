package kata.practice.concertcomparison.user

import kata.practice.concertcomparison.repository.UserRepository
import kata.practice.concertcomparison.user.model.UserModel
import kata.practice.concertcomparison.user.model.model
import kata.practice.concertcomparison.user.query.GetUserByEmail
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
internal class UserJpaReaModel(
    private val memberRepository: UserRepository
): UserReadModel {

    override fun user(getUserByEmail: GetUserByEmail): UserModel? {
        return memberRepository.findByEmail(getUserByEmail.email).getOrNull()?.model()
    }
}