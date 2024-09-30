package kata.practice.concertcomparison.application

import kata.practice.concertcomparison.application.command.AddConcert
import kata.practice.concertcomparison.application.command.AddDiscountPolicy
import kata.practice.concertcomparison.application.command.AddPrice
import kata.practice.concertcomparison.application.command.UpdatePoster
import kata.practice.concertcomparison.common.exception.EntityNotFoundException
import kata.practice.concertcomparison.model.Concert
import kata.practice.concertcomparison.model.price.PercentDiscountPolicy
import kata.practice.concertcomparison.model.price.Price
import kata.practice.concertcomparison.repository.ConcertRepository
import kata.practice.concertcomparison.repository.DiscountPolicyRepository
import kata.practice.concertcomparison.repository.PriceRepository
import kata.practice.concertcomparison.service.PosterService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull


@Service
class ConcertApplication(
    private val concertRepository: ConcertRepository,
    private val priceRepository: PriceRepository,
    private val discountPolicyRepository: DiscountPolicyRepository,
    private val posterService: PosterService,
){

    @Transactional
    fun handle(command: AddConcert){
        val concert = Concert(name = command.name, description = command.description)
        concertRepository.save(concert)

        val price = Price(
            concertId = concert.id!!,
            price = command.price,
            currency = "WON"
        )

        priceRepository.save(price)
    }

    @Transactional
    fun handle(command: UpdatePoster){
        val concert = concertRepository.findById(command.concertId)
            .getOrNull() ?: throw EntityNotFoundException()

        val concertId = concert.id!!
        val image = command.image

        val poster = posterService.poster(concertId)

        if (poster != null){
            posterService.delete(concertId)
            posterService.save(concertId, image)
        }else{
            posterService.save(concertId, image)
        }
    }

    @Transactional
    fun handle(command: AddPrice){
        val concert = concertRepository.findById(command.concertId)
            .getOrNull() ?: throw EntityNotFoundException()

        val price = Price(
            concertId = concert.id!!,
            price = command.price,
            currency = "WON"
        )
        priceRepository.save(price)
    }

    @Transactional
    fun handle(command: AddDiscountPolicy){
        val concert = concertRepository.findById(command.concertId)
            .getOrNull() ?: throw EntityNotFoundException()

        val percentDiscountPolicy = PercentDiscountPolicy(
            concertId = concert.id!!,
            name = command.name,
            percent = command.percent
        )

        percentDiscountPolicy.percent

        discountPolicyRepository.save(percentDiscountPolicy)
    }



}