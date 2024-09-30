package kata.practice.concertcomparison.reservation.querymodel

import jakarta.persistence.EntityManager
import kata.practice.concertcomparison.reservation.model.ReservationItemModel
import kata.practice.concertcomparison.reservation.query.GetReservationItems

class GetReservationItemQueryProcessor(
    private val entityManager: EntityManager,
){


    companion object{
        private const val QUERY_STRING = """
            SELECT new kata.practice.concertcomparison.reservation.querymodel.ReservationItemWithTicketWithSeat(i, t, s)
            From ReservationItem i
            JOIN Ticket t ON t.id = i.ticketId
            JOIN Seat s On s.id = t.seatId
            WHERE i.reservationId = :reservationId
        """
    }

    fun process(getReservationItems: GetReservationItems): List<ReservationItemModel>{
        return fetch(getReservationItems).map(ReservationItemWithTicketWithSeat::toModel)
    }


    private fun fetch(getReservationItems: GetReservationItems): List<ReservationItemWithTicketWithSeat>{
        return entityManager.createQuery(QUERY_STRING, ReservationItemWithTicketWithSeat::class.java)
            .setParameter("reservationId", getReservationItems.reservationId)
            .resultList
    }
}