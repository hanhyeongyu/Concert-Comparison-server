package kata.practice.concertcomparison.map.viewmodel

import kata.practice.concertcomparison.model.Seat

data class SeatViewModel(
    val id: Long,
    val mapId: Long,
    val seatInfo: String,
    val rowNum: Int,
    val seatNum: Int,
    val posTop: Int,
    val posLeft: Int
){
    constructor(seat: Seat) : this(
        id = seat.id!!,
        mapId = seat.mapId,
        seatInfo = seat.seatInfo,
        rowNum = seat.rowNum,
        seatNum = seat.seatNum,
        posTop = seat.posTop,
        posLeft = seat.posLeft
    )
}