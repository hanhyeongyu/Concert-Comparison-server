package kata.practice.concertcomparison.performance.querymodel

import kata.practice.concertcomparison.model.Location
import kata.practice.concertcomparison.model.Performance

data class PerformanceWithLocation(
    val performance: Performance,
    val location: Location
)