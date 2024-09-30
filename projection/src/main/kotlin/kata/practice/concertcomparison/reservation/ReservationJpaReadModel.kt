package kata.practice.concertcomparison.reservation

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import kata.practice.concertcomparison.Page
import kata.practice.concertcomparison.repository.ReservationRepository
import kata.practice.concertcomparison.reservation.model.ReservationItemModel
import kata.practice.concertcomparison.reservation.model.ReservationViewModel
import kata.practice.concertcomparison.reservation.query.GetReservation
import kata.practice.concertcomparison.reservation.query.GetReservationItems
import kata.practice.concertcomparison.reservation.query.GetReservations
import kata.practice.concertcomparison.reservation.querymodel.GetReservationItemQueryProcessor
import kata.practice.concertcomparison.reservation.querymodel.GetReservationQueryProcessor
import kata.practice.concertcomparison.service.PosterService
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull


@Service
internal class ReservationJpaReadModel(
    private val reservationRepository: ReservationRepository,
    private val posterService: PosterService
): ReservationReadModel {

    @PersistenceContext
    private lateinit var entityManager: EntityManager
    override fun reservation(getReservation: GetReservation): ReservationViewModel? {
        return reservationRepository.findByReservationId(getReservation.reservationId)
            .getOrNull()
            ?.let(::ReservationViewModel)
    }

    override fun reservations(getReservations: GetReservations): Page<ReservationViewModel> {
        val queryProcessor = GetReservationQueryProcessor(entityManager)
        val result = queryProcessor.process(getReservations).map{ reservation ->
            val viewModel = ReservationViewModel(reservation)
            viewModel.posterURL = posterService.url(reservation.concertId)
            return@map viewModel
        }
        return result
    }

    override fun reservationItems(getReservationItems: GetReservationItems): List<ReservationItemModel> {
        val queryProcessor = GetReservationItemQueryProcessor(entityManager)
        return queryProcessor.process(getReservationItems)
    }

}