package kata.practice.concertcomparison.map.viewmodel

data class MapsViewModel(
    val id: Long,
    val locationId: Long,
    val width: Int,
    val height: Int,
    val seats: List<SeatViewModel>
)