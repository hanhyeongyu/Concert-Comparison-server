package kata.practice.concertcomparison.performance

import kata.practice.concertcomparison.performance.PerformancesController.Companion.ENDPOINT
import kata.practice.concertcomparison.performance.query.GetPerformance
import kata.practice.concertcomparison.performance.viewmodel.PerformanceViewModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(ENDPOINT)
class PerformancesController(
    private val performanceReadModel: PerformanceReadModel
){

    @GetMapping
    fun performances(@RequestParam concertId: Long): ResponseEntity<List<PerformanceViewModel>>{
        val query = GetPerformance(concertId)
        val result = performanceReadModel.performance(query)
        return ResponseEntity.ok(result)
    }

    companion object{
        internal const val ENDPOINT = "performances"
    }
}