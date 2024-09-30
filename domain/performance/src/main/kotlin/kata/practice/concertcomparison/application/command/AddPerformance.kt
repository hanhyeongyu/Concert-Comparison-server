package kata.practice.concertcomparison.application.command

import java.time.LocalDateTime

data class AddPerformance(
    val concertId: Long,
    val locationId: Long,
    val mapsId: Long,
    val performanceName: String,
    val performanceAt: LocalDateTime
)