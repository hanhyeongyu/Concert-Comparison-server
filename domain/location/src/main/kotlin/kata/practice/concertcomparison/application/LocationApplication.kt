package kata.practice.concertcomparison.application

import kata.practice.concertcomparison.application.command.AddLocation
import kata.practice.concertcomparison.model.Address
import kata.practice.concertcomparison.model.Location
import kata.practice.concertcomparison.repository.LocationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class LocationApplication(
    private val locationRepository: LocationRepository
){

    @Transactional
    fun handle(command: AddLocation){
        val location = Location(
            name = command.name,
            address = Address(
                city = command.city,
                street = command.street,
                zipCode = command.zipCode
            )
        )
        locationRepository.save(location)
    }


}