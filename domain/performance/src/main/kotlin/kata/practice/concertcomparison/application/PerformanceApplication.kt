package kata.practice.concertcomparison.application

import kata.practice.concertcomparison.application.command.AddPerformance
import kata.practice.concertcomparison.common.exception.EntityNotFoundException
import kata.practice.concertcomparison.model.Performance
import kata.practice.concertcomparison.repository.ConcertRepository
import kata.practice.concertcomparison.repository.LocationRepository
import kata.practice.concertcomparison.repository.MapsRepository
import kata.practice.concertcomparison.repository.PerformanceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Service
class PerformanceApplication(
    private val performanceRepository: PerformanceRepository,
    private val concertRepository: ConcertRepository,
    private val locationRepository: LocationRepository,
    private val mapsRepository: MapsRepository,
){


    @Transactional
    fun handle(command: AddPerformance){
        val concert = concertRepository.findById(command.concertId)
            .getOrNull() ?: throw EntityNotFoundException()

        val location = locationRepository.findById(command.locationId)
            .getOrNull() ?: throw EntityNotFoundException()

        val maps = mapsRepository.findById(command.mapsId)
            .getOrNull() ?: throw EntityNotFoundException()

        val performance = Performance(
            concertId = concert.id!!,
            locationId = location.id!!,
            mapId = maps.id!!,
            name = command.performanceName,
            performanceAt = command.performanceAt
        )
        performanceRepository.save(performance)
    }

}

