package kata.practice.concertcomparison.reservation

import kata.practice.concertcomparison.Page
import kata.practice.concertcomparison.application.ReservationApplication
import kata.practice.concertcomparison.application.command.AddReservation
import kata.practice.concertcomparison.argumentResolver.UserId
import kata.practice.concertcomparison.reservation.ReservationsController.Companion.ENDPOINT
import kata.practice.concertcomparison.reservation.model.ReservationItemModel
import kata.practice.concertcomparison.reservation.model.ReservationViewModel
import kata.practice.concertcomparison.reservation.query.GetReservation
import kata.practice.concertcomparison.reservation.query.GetReservationItems
import kata.practice.concertcomparison.reservation.query.GetReservations
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping(ENDPOINT)
class ReservationsController(
    private val reservationApplication: ReservationApplication,
    private val reservationReadModel: ReservationReadModel
){
    @GetMapping()
    fun reservations(
        @RequestParam continuationToken: String?,
        @UserId userId: Long
    ): ResponseEntity<Page<ReservationViewModel>> {
        val query = GetReservations(userId, continuationToken)
        val result = reservationReadModel.reservations(query)
        return ResponseEntity.ok(result)
    }

    @GetMapping("{reservationId}")
    fun reservation(@PathVariable reservationId: UUID): ResponseEntity<ReservationViewModel> {
        val query = GetReservation(reservationId)
        val result = reservationReadModel.reservation(query)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/items")
    fun reservationItems(@RequestParam reservationId: UUID): ResponseEntity<List<ReservationItemModel>>{
        val query = GetReservationItems(reservationId)
        val result = reservationReadModel.reservationItems(query)
        return ResponseEntity.ok(result)
    }

    @PostMapping
    fun addReservation(
        @RequestBody request: ReservationsRequests.AddReservations,
        @UserId userId: Long,
    ): ResponseEntity<ReservationViewModel>{
        val reservationId = UUID.randomUUID()
        val command = AddReservation(
            reservationId = reservationId,
            userId = userId,
            paymentId = request.paymentKey,
            performanceId = request.performanceId,
            ticketIds = request.ticketIds
        )
        reservationApplication.handle(command)

        val query = GetReservation(reservationId)
        val result = reservationReadModel.reservation(query)
        return ResponseEntity.ok(result)
    }

    companion object{
        internal const val ENDPOINT = "reservations"
    }
}