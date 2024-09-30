package kata.practice.concertcomparison.admin

import kata.practice.concertcomparison.admin.AdminPostersController.Companion.ENDPOINT
import kata.practice.concertcomparison.application.ConcertApplication
import kata.practice.concertcomparison.application.command.UpdatePoster
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping(ENDPOINT)
class AdminPostersController(
    private val concertApplication: ConcertApplication,
){

    @PostMapping
    fun add(
        @RequestPart image: MultipartFile,
        @RequestPart concertId: Long,
    ): ResponseEntity<Void>{
        val command = UpdatePoster(concertId, image)
        concertApplication.handle(command)
        return ResponseEntity.ok().build()
    }


    companion object{
        internal const val ENDPOINT = "admin/posters"
    }
}