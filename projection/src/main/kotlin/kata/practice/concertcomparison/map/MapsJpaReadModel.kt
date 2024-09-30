package kata.practice.concertcomparison.map

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import kata.practice.concertcomparison.map.query.GetMaps
import kata.practice.concertcomparison.map.query.GetSeats
import kata.practice.concertcomparison.map.viewmodel.MapsViewModel
import kata.practice.concertcomparison.map.viewmodel.SeatViewModel
import kata.practice.concertcomparison.repository.MapsRepository
import kata.practice.concertcomparison.repository.SeatRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
internal class MapsJpaReadModel(
    private val mapsRepository: MapsRepository,
    private val seatsRepository: SeatRepository,
): MapsReadModel{

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun maps(getMaps: GetMaps): MapsViewModel? {
        val maps = mapsRepository.findById(getMaps.mapId).getOrNull() ?: return null
        val seats = seatsRepository.findAllByMapId(maps.id!!)

        return MapsViewModel(
            id =  maps.id!!,
            locationId = maps.locationId,
            width = maps.width,
            height = maps.height,
            seats = seats.map(::SeatViewModel)
        )
    }

    override fun seats(getSeats: GetSeats): List<SeatViewModel> {
        return  seatsRepository.findAllByMapId(getSeats.mapId)
            .map(::SeatViewModel)
    }
}