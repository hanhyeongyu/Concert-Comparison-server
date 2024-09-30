package kata.practice.concertcomparison.model.reservationItem

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import kata.practice.concertcomparison.BaseEntity
import java.util.UUID


@Entity
class ReservationItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var reservationId: UUID,

    var ticketId: Long
): BaseEntity()