package kata.practice.concertcomparison.concert.viewmodel

import kata.practice.concertcomparison.model.Concert

data class ConcertViewModel(
    val id: Long,
    val name: String,
    val description: String,
    var posterURL: String?
){
    constructor(concert: Concert): this(
        id = concert.id!!,
        name = concert.name,
        description = concert.description,
        posterURL = null,
    )
}
