package kata.practice.concertcomparison.performance.viewmodel

import kata.practice.concertcomparison.location.viewmodel.LocationViewModel
import kata.practice.concertcomparison.performance.querymodel.PerformanceWithLocation
import java.time.LocalDateTime

data class PerformanceViewModel(
    var id: Long,
    val concertId: Long,
    val mapId: Long,
    val name: String,
    var performanceAt: LocalDateTime,
    val location: LocationViewModel,
){
    constructor(performanceWithLocation: PerformanceWithLocation): this(
        id = performanceWithLocation.performance.id!!,
        concertId = performanceWithLocation.performance.concertId,
        mapId = performanceWithLocation.performance.mapId,
        name = performanceWithLocation.performance.name,
        performanceAt = performanceWithLocation.performance.performanceAt,
        location =  LocationViewModel(performanceWithLocation.location)
    )
}