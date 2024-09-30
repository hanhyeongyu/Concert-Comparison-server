package kata.practice.concertcomparison.repository

import kata.practice.concertcomparison.model.Concert
import org.springframework.data.jpa.repository.JpaRepository

interface ConcertRepository: JpaRepository<Concert, Long>