package kata.practice.concertcomparison.repository

import kata.practice.concertcomparison.model.Maps
import org.springframework.data.jpa.repository.JpaRepository

interface MapsRepository: JpaRepository<Maps, Long>