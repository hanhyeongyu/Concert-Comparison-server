package kata.practice.concertcomparison.repository

import kata.practice.concertcomparison.model.reservationItem.ReservationItem
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ReservationItemRepository: JpaRepository<ReservationItem, Long>{
    fun  findAllByReservationId(reservationId: UUID): List<ReservationItem>
}