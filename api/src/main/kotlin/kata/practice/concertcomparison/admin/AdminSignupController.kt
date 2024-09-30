package kata.practice.concertcomparison.admin

import kata.practice.concertcomparison.admin.AdminSignupController.Companion.ENDPOINT
import kata.practice.concertcomparison.application.UserApplication
import kata.practice.concertcomparison.application.command.Signup
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(ENDPOINT)
class AdminSignupController(
    private val memberApplication: UserApplication
){

    @PostMapping
    fun signup(@RequestBody command: Signup): ResponseEntity<Void> {
        memberApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    companion object{
        internal const val ENDPOINT = "admin/signup"
    }
}