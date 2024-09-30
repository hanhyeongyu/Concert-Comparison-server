package kata.practice.concertcomparison.concert.querymodel

import jakarta.persistence.EntityManager
import kata.practice.concertcomparison.Page
import kata.practice.concertcomparison.model.Concert
import kata.practice.concertcomparison.concert.query.GetConcerts

class GetConcertsQueryProcessor(
    private val entityManager: EntityManager
){

    companion object{
        private const val QUERY_STRING = """
            SELECT c
            FROM  Concert c
            WHERE (:lastEvaluatedId IS NULL OR c.id < :lastEvaluatedId)
            ORDER BY c.id DESC 
        """

        private const val PAGE_SIZE = 10
    }

    fun process(getConcerts: GetConcerts): Page<Concert> {
        return putOnPage(fetch(getConcerts))
    }

    private fun fetch(getConcerts: GetConcerts): List<Concert>{
        return entityManager.createQuery(QUERY_STRING, Concert::class.java)
            .setParameter("lastEvaluatedId", getConcerts.continuationToken)
            .setMaxResults(PAGE_SIZE)
            .resultList
    }

    private fun putOnPage(result: List<Concert>): Page<Concert>{
        return Page(result, lastEvaluatedId(result))
    }

    private fun lastEvaluatedId(result: List<Concert>): String?{
        if (result.count() == PAGE_SIZE){
            return result.last().id.toString()
        }else{
            return null
        }
    }

}