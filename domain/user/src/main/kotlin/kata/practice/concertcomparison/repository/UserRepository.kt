package kata.practice.concertcomparison.repository

import kata.practice.concertcomparison.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, Long>{
    fun findByEmail(email: String): Optional<User>
}