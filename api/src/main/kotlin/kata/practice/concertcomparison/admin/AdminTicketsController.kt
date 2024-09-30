package kata.practice.concertcomparison.admin

import kata.practice.concertcomparison.admin.AdminTicketsController.Companion.ENDPOINT
import kata.practice.concertcomparison.application.TicketApplication
import kata.practice.concertcomparison.application.command.AddTickets
import kata.practice.concertcomparison.map.query.GetSeats
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ENDPOINT)
class AdminTicketsController(
    private val ticketApplication: TicketApplication,
){

    @PostMapping
    fun addTickets(@RequestBody command: AddTickets): ResponseEntity<Void>{
        ticketApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    companion object{
        internal const val ENDPOINT = "admin/tickets"
    }
}