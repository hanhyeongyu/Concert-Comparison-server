package kata.practice.concertcomparison.admin

import kata.practice.concertcomparison.admin.AdminSeatsController.Companion.ENDPOINT
import kata.practice.concertcomparison.application.MapsApplication
import kata.practice.concertcomparison.application.command.AddSeats
import kata.practice.concertcomparison.map.MapsReadModel
import kata.practice.concertcomparison.map.query.GetSeats
import kata.practice.concertcomparison.map.viewmodel.SeatViewModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(ENDPOINT)
class AdminSeatsController(
    private val mapsApplication: MapsApplication,
    private val mapsReadModel: MapsReadModel,
){

    @GetMapping("{mapId}")
    fun seats(@PathVariable mapId: Long): ResponseEntity<List<SeatViewModel>> {
        val getSeats = GetSeats(mapId)
        val result = mapsReadModel.seats(getSeats)
        return ResponseEntity.ok(result)
    }

    @PostMapping()
    fun add(@RequestBody command: AddSeats): ResponseEntity<Void> {
        mapsApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    companion object{
        internal const val ENDPOINT = "admin/seats"
    }
}