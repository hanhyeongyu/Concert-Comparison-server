package kata.practice.concertcomparison.repository

import kata.practice.concertcomparison.model.Ticket
import org.springframework.data.jpa.repository.JpaRepository

interface TicketRepository: JpaRepository<Ticket, Long>{
    fun findAllByPerformanceId(performanceId: Long): List<Ticket>
}