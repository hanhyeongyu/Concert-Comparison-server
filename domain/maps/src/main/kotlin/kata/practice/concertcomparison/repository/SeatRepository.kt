package kata.practice.concertcomparison.repository

import kata.practice.concertcomparison.model.Seat
import org.springframework.data.jpa.repository.JpaRepository

interface SeatRepository: JpaRepository<Seat, Long>{
    fun findAllByMapId(mapId: Long): List<Seat>
}