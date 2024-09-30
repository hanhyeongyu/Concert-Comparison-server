package kata.practice.concertcomparison.model

import jakarta.persistence.*
import kata.practice.concertcomparison.BaseEntity
import java.time.LocalDateTime
import java.util.*


@Entity
class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true)
    val reservationId: UUID,

    val userId: Long,

    val concertId: Long,

    val performanceId: Long,

    val locationId: Long,

    val mapsId: Long,

    val paymentId: UUID,


    @Enumerated(EnumType.STRING)
    var status: Status,

    val reservationAt: LocalDateTime,

    val concertName: String,

    val performanceName: String,

    val performanceDate: LocalDateTime,

    val locationName: String,

    val numberOfTickets: Int,

    val totalPrice: Long,
): BaseEntity(){

    enum class Status{
        RESERVATION,
        CANCEL
    }

}