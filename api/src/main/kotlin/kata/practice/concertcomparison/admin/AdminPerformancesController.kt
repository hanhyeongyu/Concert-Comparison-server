package kata.practice.concertcomparison.admin

import kata.practice.concertcomparison.admin.AdminPerformancesController.Companion.ENDPOINT
import kata.practice.concertcomparison.application.PerformanceApplication
import kata.practice.concertcomparison.application.command.AddPerformance
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(ENDPOINT)
class AdminPerformancesController(
    private val performanceApplication: PerformanceApplication
){


    @PostMapping
    fun addPerformance(@RequestBody command: AddPerformance): ResponseEntity<Void>{
        performanceApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    companion object{
        internal const val ENDPOINT = "admin/performances"
    }

}