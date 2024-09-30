package kata.practice.concertcomparison.performance

import kata.practice.concertcomparison.performance.query.GetPerformance
import kata.practice.concertcomparison.performance.viewmodel.PerformanceViewModel

interface PerformanceReadModel {
    fun performance(getPerformance: GetPerformance): List<PerformanceViewModel>
}