package kata.practice.concertcomparison.admin

import kata.practice.concertcomparison.admin.AdminLocationsController.Companion.ENDPOINT
import kata.practice.concertcomparison.application.LocationApplication
import kata.practice.concertcomparison.application.command.AddLocation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(ENDPOINT)
class AdminLocationsController(
    private val locationApplication: LocationApplication
){

    @PostMapping
    fun add(@RequestBody command: AddLocation): ResponseEntity<Void>{
        locationApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    companion object{
        internal const val ENDPOINT = "admin/locations"
    }
}