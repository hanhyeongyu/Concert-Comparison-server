package kata.practice.concertcomparison.reservation.querymodel

import jakarta.persistence.EntityManager
import kata.practice.concertcomparison.Page
import kata.practice.concertcomparison.model.Reservation
import kata.practice.concertcomparison.reservation.model.ReservationViewModel
import kata.practice.concertcomparison.reservation.query.GetReservations

class GetReservationQueryProcessor(
    private val entityManager: EntityManager
){

    companion object{
        private const val QUERY_STRING = """
            SELECT r
            FROM  Reservation r
            WHERE r.userId = :userId
            AND (:lastEvaluatedId IS NULL OR r.id < :lastEvaluatedId)
            ORDER BY r.id DESC 
        """

        private const val PAGE_SIZE = 10
    }

    fun process(getReservations: GetReservations): Page<Reservation> {
        return putOnPage(fetch(getReservations))
    }

    private fun fetch(getReservations: GetReservations): List<Reservation>{
        return entityManager.createQuery(QUERY_STRING, Reservation::class.java)
            .setParameter("userId", getReservations.userId)
            .setParameter("lastEvaluatedId", getReservations.continuationToken)
            .setMaxResults(PAGE_SIZE)
            .resultList
    }

    private fun putOnPage(result: List<Reservation>): Page<Reservation> {
        return Page(result, lastEvaluatedId(result))
    }

    private fun lastEvaluatedId(result: List<Reservation>): String?{
        if (result.count() == PAGE_SIZE){
            return result.last().id.toString()
        }else{
            return null
        }
    }

}