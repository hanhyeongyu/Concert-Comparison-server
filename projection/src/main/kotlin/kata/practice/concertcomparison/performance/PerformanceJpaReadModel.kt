package kata.practice.concertcomparison.performance

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import kata.practice.concertcomparison.performance.query.GetPerformance
import kata.practice.concertcomparison.performance.querymodel.GetPerformanceQueryProcessor
import kata.practice.concertcomparison.performance.viewmodel.PerformanceViewModel
import org.springframework.stereotype.Service

@Service
class PerformanceJpaReadModel: PerformanceReadModel {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun performance(getPerformance: GetPerformance): List<PerformanceViewModel> {
        val queryProcessor = GetPerformanceQueryProcessor(entityManager)
        val result = queryProcessor.process(getPerformance)
        return result
    }
}