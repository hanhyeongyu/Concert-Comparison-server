package kata.practice.concertcomparison.repository

import kata.practice.concertcomparison.model.Performance
import org.springframework.data.jpa.repository.JpaRepository

interface PerformanceRepository: JpaRepository<Performance, Long>{
    fun findAllByConcertId(concertId: Long): List<Performance>
}