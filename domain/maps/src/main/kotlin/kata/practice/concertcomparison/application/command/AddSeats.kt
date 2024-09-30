package kata.practice.concertcomparison.application.command

data class AddSeats(
    val seats: List<AddSeat>
)

data class AddSeat(
    val mapId: Long,
    val seatInfo: String,
    val rowNum: Int,
    val seatNum: Int,
    val posTop: Int,
    val posLeft: Int
)