package kata.practice.concertcomparison.maps

import kata.practice.concertcomparison.map.MapsReadModel
import kata.practice.concertcomparison.map.viewmodel.SeatViewModel
import kata.practice.concertcomparison.map.query.GetSeats
import kata.practice.concertcomparison.maps.SeatsController.Companion.ENDPOINT
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ENDPOINT)
class SeatsController(
    private val mapsReadModel: MapsReadModel
){


    @GetMapping
    fun seats(@RequestParam mapsId: Long): ResponseEntity<List<SeatViewModel>>{
        val query = GetSeats(mapsId)
        val result = mapsReadModel.seats(query)
        return ResponseEntity.ok(result)
    }

    companion object{
        internal const val ENDPOINT = "seats"
    }
}