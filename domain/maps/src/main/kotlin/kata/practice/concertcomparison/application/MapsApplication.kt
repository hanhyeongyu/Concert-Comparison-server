package kata.practice.concertcomparison.application

import kata.practice.concertcomparison.application.command.AddMaps
import kata.practice.concertcomparison.application.command.AddSeat
import kata.practice.concertcomparison.application.command.AddSeats
import kata.practice.concertcomparison.common.exception.EntityNotFoundException
import kata.practice.concertcomparison.model.Maps
import kata.practice.concertcomparison.model.Seat
import kata.practice.concertcomparison.repository.MapsRepository
import kata.practice.concertcomparison.repository.SeatRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Service
class MapsApplication(
    private val mapsRepository: MapsRepository,
    private val seatRepository: SeatRepository,
){

    @Transactional
    fun handle(command: AddMaps){
        val newMap = Maps(
            locationId = command.locationId,
            width = command.width,
            height = command.height
        )

        mapsRepository.save(newMap)
    }

    @Transactional
    fun handle(command: AddSeats){
        command.seats.forEach { handle(it) }
    }

    @Transactional
    fun handle(command: AddSeat){
        val map = mapsRepository.findById(command.mapId)
            .getOrNull() ?: throw EntityNotFoundException()

        val seats = Seat(
            mapId = map.id!!,
            seatInfo = command.seatInfo,
            rowNum = command.rowNum,
            seatNum = command.seatNum,
            posLeft = command.posLeft,
            posTop = command.posTop
        )
        seatRepository.save(seats)
    }
}