package kata.practice.concertcomparison.concert

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import kata.practice.concertcomparison.Page
import kata.practice.concertcomparison.concert.query.GetConcerts
import kata.practice.concertcomparison.concert.querymodel.GetConcertsQueryProcessor
import kata.practice.concertcomparison.concert.viewmodel.ConcertViewModel
import kata.practice.concertcomparison.model.Concert
import kata.practice.concertcomparison.service.PosterService
import org.springframework.stereotype.Service


@Service
internal class ConcertJpaReadModel(
    private val posterService: PosterService
): ConcertReadModel {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun concerts(getConcerts: GetConcerts): Page<ConcertViewModel> {
        val queryProcessor = GetConcertsQueryProcessor(entityManager)
        val result = queryProcessor.process(getConcerts).map { concert ->
            val viewModel = ConcertViewModel(concert)
            viewModel.posterURL = posterUrl(concert)
            return@map viewModel
        }
        return result
    }

    private fun posterUrl(concert: Concert): String?{
        return posterService.url(concert.id!!)
    }
}