package kata.practice.concertcomparison.admin

import kata.practice.concertcomparison.admin.AdminMapsController.Companion.ENDPOINT
import kata.practice.concertcomparison.application.MapsApplication
import kata.practice.concertcomparison.application.command.AddMaps
import kata.practice.concertcomparison.common.exception.EntityNotFoundException
import kata.practice.concertcomparison.map.MapsReadModel
import kata.practice.concertcomparison.map.query.GetMaps
import kata.practice.concertcomparison.map.viewmodel.MapsViewModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(ENDPOINT)
class AdminMapsController(
    private val mapsApplication: MapsApplication,
    private val mapsReadModel: MapsReadModel
){

    @GetMapping("{mapsId}")
    fun getMap(@PathVariable mapsId: Long): ResponseEntity<MapsViewModel> {
        val getMaps = GetMaps(mapsId)
        val result = mapsReadModel.maps(getMaps) ?: throw EntityNotFoundException()
        return ResponseEntity.ok(result)
    }

    @PostMapping()
    fun addMap(@RequestBody command: AddMaps): ResponseEntity<Void> {
        mapsApplication.handle(command)
        return ResponseEntity.ok().build()
    }


    companion object{
        internal const val ENDPOINT = "admin/maps"
    }
}