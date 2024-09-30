package kata.practice.concertcomparison.reservation

import kata.practice.concertcomparison.Page
import kata.practice.concertcomparison.reservation.model.ReservationItemModel
import kata.practice.concertcomparison.reservation.model.ReservationViewModel
import kata.practice.concertcomparison.reservation.query.GetReservation
import kata.practice.concertcomparison.reservation.query.GetReservationItems
import kata.practice.concertcomparison.reservation.query.GetReservations

interface ReservationReadModel {
    fun reservation(getReservation: GetReservation): ReservationViewModel?
    fun reservations(getReservations: GetReservations): Page<ReservationViewModel>

    fun reservationItems(getReservationItems: GetReservationItems): List<ReservationItemModel>
}