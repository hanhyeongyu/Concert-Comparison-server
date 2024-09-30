package kata.practice.concertcomparison.repository

import kata.practice.concertcomparison.model.Poster
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional


interface PosterRepository: JpaRepository<Poster, Long>{
    fun findByConcertId(concertId: Long): Optional<Poster>
}