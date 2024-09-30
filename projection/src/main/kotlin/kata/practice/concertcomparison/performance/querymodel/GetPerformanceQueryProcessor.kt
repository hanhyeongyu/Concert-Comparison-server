package kata.practice.concertcomparison.performance.querymodel

import jakarta.persistence.EntityManager
import kata.practice.concertcomparison.performance.query.GetPerformance

import kata.practice.concertcomparison.performance.viewmodel.PerformanceViewModel

class GetPerformanceQueryProcessor(
    private val entityManager: EntityManager
){


    companion object{
        private const val QUERY_STRING = """
            SELECT new kata.practice.concertcomparison.performance.querymodel.PerformanceWithLocation(p, l)
            FROM Performance p
            INNER JOIN Location l ON l.id = p.locationId
            WHERE p.concertId = :concertId
        """
    }

    fun process(getPerformance: GetPerformance): List<PerformanceViewModel>{
        val result = fetch(getPerformance).map(::PerformanceViewModel)
        return result
    }

    private fun fetch(getPerformance: GetPerformance): List<PerformanceWithLocation>{
        return entityManager.createQuery(QUERY_STRING, PerformanceWithLocation::class.java)
            .setParameter("concertId", getPerformance.concertId)
            .resultList
    }



}