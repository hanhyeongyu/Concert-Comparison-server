package kata.practice.concertcomparison.concert

import kata.practice.concertcomparison.Page
import kata.practice.concertcomparison.concert.viewmodel.ConcertViewModel
import kata.practice.concertcomparison.concert.query.GetConcerts

interface ConcertReadModel {
    fun concerts(getConcerts: GetConcerts): Page<ConcertViewModel>
}