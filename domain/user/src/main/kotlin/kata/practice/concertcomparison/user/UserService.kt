package kata.practice.concertcomparison.user

import kata.practice.concertcomparison.user.command.Register

interface UserService {
    fun register(register: Register)
}