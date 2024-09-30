package kata.practice.concertcomparison.location

import kata.practice.concertcomparison.location.query.GetLocation
import kata.practice.concertcomparison.location.viewmodel.LocationViewModel
import kata.practice.concertcomparison.repository.LocationRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class LocationJpaReadModel(
    private val locationRepository: LocationRepository
): LocationReadModel {
    override fun location(getLocation: GetLocation): LocationViewModel? {
        val location = locationRepository.findById(getLocation.locationId)
            .getOrNull() ?: return null

        val result = LocationViewModel(location)
        return result
    }
}