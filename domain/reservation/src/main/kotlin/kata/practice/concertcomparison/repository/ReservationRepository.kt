package kata.practice.concertcomparison.repository

import kata.practice.concertcomparison.model.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ReservationRepository: JpaRepository<Reservation, Long>{
    fun findByReservationId(reservationId: UUID): Optional<Reservation>
}