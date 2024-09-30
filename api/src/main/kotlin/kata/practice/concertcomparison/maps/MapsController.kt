package kata.practice.concertcomparison.maps

import kata.practice.concertcomparison.common.exception.EntityNotFoundException
import kata.practice.concertcomparison.map.MapsReadModel
import kata.practice.concertcomparison.map.viewmodel.MapsViewModel
import kata.practice.concertcomparison.map.query.GetMaps
import kata.practice.concertcomparison.maps.MapsController.Companion.ENDPOINT
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ENDPOINT)
class MapsController(
    private val mapsReadModel: MapsReadModel
){

    @GetMapping("{mapsId}")
    fun getMap(@PathVariable mapsId: Long): ResponseEntity<MapsViewModel>{
        val query = GetMaps(mapsId)
        val result = mapsReadModel.maps(query) ?: throw EntityNotFoundException()
        return ResponseEntity.ok(result)
    }


    companion object{
        internal const val ENDPOINT = "maps"
    }
}