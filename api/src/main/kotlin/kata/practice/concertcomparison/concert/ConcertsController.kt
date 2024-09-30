package kata.practice.concertcomparison.concert

import kata.practice.concertcomparison.Page
import kata.practice.concertcomparison.concert.ConcertsController.Companion.ENDPOINT
import kata.practice.concertcomparison.concert.viewmodel.ConcertViewModel
import kata.practice.concertcomparison.concert.query.GetConcerts
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ENDPOINT)
class ConcertsController(
    private val concertReadModel: ConcertReadModel
){

    @GetMapping()
    fun concerts(@RequestParam continuationToken: String?): ResponseEntity<Page<ConcertViewModel>>{
        val query = GetConcerts(continuationToken)
        val result = concertReadModel.concerts(query)
        return ResponseEntity.ok(result)
    }

    companion object{
        internal const val ENDPOINT = "concerts"
    }
}