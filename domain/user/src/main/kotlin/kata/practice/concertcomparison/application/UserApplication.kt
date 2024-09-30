package kata.practice.concertcomparison.application

import kata.practice.concertcomparison.application.command.Signup
import kata.practice.concertcomparison.codable.PasswordEncodable
import kata.practice.concertcomparison.user.UserService
import kata.practice.concertcomparison.user.command.Register
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserApplication(
    private val userService: UserService,
    private val passwordEncodable: PasswordEncodable,
){

    @Transactional
    fun handle(command: Signup) {
        val encodedPassword = passwordEncodable.encode(command.password)
        val register = Register(email = command.email, encodedPassword= encodedPassword)
        userService.register(register)
    }
}