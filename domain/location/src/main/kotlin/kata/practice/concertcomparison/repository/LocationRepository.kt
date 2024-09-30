package kata.practice.concertcomparison.repository

import kata.practice.concertcomparison.model.Location
import org.springframework.data.jpa.repository.JpaRepository

interface LocationRepository: JpaRepository<Location, Long>