package kata.practice.concertcomparison.repository

import kata.practice.concertcomparison.model.price.Price
import org.springframework.data.jpa.repository.JpaRepository

interface PriceRepository: JpaRepository<Price, Long>