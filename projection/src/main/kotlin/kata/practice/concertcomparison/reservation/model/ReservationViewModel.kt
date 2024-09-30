package kata.practice.concertcomparison.reservation.model

import kata.practice.concertcomparison.model.Reservation
import java.time.LocalDateTime
import java.util.*


data class ReservationViewModel(
    var id: Long,
    val reservationId: UUID,
    val userId: Long,
    val concertId: Long,
    val performanceId: Long,
    val locationId: Long,
    val mapsId: Long,
    val paymentId: UUID,
    val reservationAt: LocalDateTime,
    val concertName: String,
    val performanceName: String,
    val performanceDate: LocalDateTime,
    val locationName: String,
    val numberOfTickets: Int,
    val totalPrice: Long,
    var status: Status,
    var posterURL: String?
) {
    enum class Status {
        RESERVATION,
        CANCEL
    }

    constructor(reservation: Reservation): this(
        id = reservation.id!!,
        reservationId = reservation.reservationId,
        userId = reservation.userId,
        concertId = reservation.concertId,
        performanceId = reservation.performanceId,
        locationId = reservation.locationId,
        mapsId = reservation.mapsId,
        paymentId = reservation.paymentId,
        reservationAt = reservation.reservationAt,
        concertName = reservation.concertName,
        performanceName = reservation.performanceName,
        performanceDate = reservation.performanceDate,
        locationName = reservation.locationName,
        numberOfTickets = reservation.numberOfTickets,
        totalPrice = reservation.totalPrice,
        status = when (reservation.status) {
            Reservation.Status.RESERVATION -> Status.RESERVATION
            Reservation.Status.CANCEL -> Status.CANCEL
        },
        posterURL = null
    )
}

