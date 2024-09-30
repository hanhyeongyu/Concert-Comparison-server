package kata.practice.concertcomparison.user

import kata.practice.concertcomparison.user.command.Register
import kata.practice.concertcomparison.repository.UserRepository
import org.springframework.stereotype.Service


@Service
internal class UserServiceImp(
    private val userRepository: UserRepository
): UserService {
    override fun register(register: Register) {
        val user = User(
            email =  Email(register.email),
            encodedPassword = EncodedPassword(register.encodedPassword)
        )
        userRepository.save(user)
    }


}