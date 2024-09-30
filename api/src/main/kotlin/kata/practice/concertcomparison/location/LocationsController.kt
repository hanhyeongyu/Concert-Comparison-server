package kata.practice.concertcomparison.location

import kata.practice.concertcomparison.location.LocationsController.Companion.ENDPOINT
import kata.practice.concertcomparison.location.viewmodel.LocationViewModel
import kata.practice.concertcomparison.location.query.GetLocation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(ENDPOINT)
class LocationsController(
    private val locationReadModel: LocationReadModel
){

    @GetMapping("{id}")
    fun location(@PathVariable id: Long): ResponseEntity<LocationViewModel>{
        val query = GetLocation(id)
        val result = locationReadModel.location(query)
        return ResponseEntity.ok(result)
    }

    companion object{
        internal const val ENDPOINT = "locations"
    }
}