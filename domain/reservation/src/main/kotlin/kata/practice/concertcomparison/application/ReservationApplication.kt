package kata.practice.concertcomparison.application

import kata.practice.concertcomparison.application.command.AddReservation
import kata.practice.concertcomparison.common.exception.EntityNotFoundException
import kata.practice.concertcomparison.repository.*
import kata.practice.concertcomparison.model.Reservation
import kata.practice.concertcomparison.model.Reservation.Status.RESERVATION
import kata.practice.concertcomparison.model.reservationItem.ReservationItem
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@Service
class ReservationApplication(
    private val reservationRepository: ReservationRepository,
    private val reservationItemRepository: ReservationItemRepository,
    private val concertRepository: ConcertRepository,
    private val performanceRepository: PerformanceRepository,
    private val mapsRepository: MapsRepository,
    private val locationRepository: LocationRepository,
    private val paymentRepository: PaymentRepository,
){

    @Transactional
    fun handle(command: AddReservation){

        val performance = performanceRepository.findById(command.performanceId).getOrNull() ?: throw EntityNotFoundException()
        val concert = concertRepository.findById(performance.concertId).getOrNull() ?: throw EntityNotFoundException()
        val maps = mapsRepository.findById(performance.mapId).getOrNull() ?: throw EntityNotFoundException()
        val location = locationRepository.findById(maps.locationId).getOrNull() ?: throw EntityNotFoundException()
        val payment = paymentRepository.findByPaymentId(command.paymentId).getOrNull() ?: throw EntityNotFoundException()


        val reservation = Reservation(
            reservationId = command.reservationId,
            userId = command.userId,
            concertId = concert.id!!,
            performanceId = performance.id!!,
            locationId = location.id!!,
            mapsId = maps.id!!,
            paymentId = payment.paymentId,
            status = RESERVATION,
            reservationAt = LocalDateTime.now(),
            concertName = concert.name,
            performanceName = performance.name,
            performanceDate = performance.performanceAt,
            locationName = location.name,
            numberOfTickets = command.ticketIds.count(),
            totalPrice = payment.amount
        )

        reservationRepository.save(reservation)

        val newOrderItems = command.ticketIds.map { ticketId ->
            ReservationItem(
                reservationId = reservation.reservationId,
                ticketId = ticketId,
            )
        }
        reservationItemRepository.saveAll(newOrderItems)
    }
}