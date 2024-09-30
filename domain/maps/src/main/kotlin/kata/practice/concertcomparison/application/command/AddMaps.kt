package kata.practice.concertcomparison.application.command

data class AddMaps(
    val locationId: Long,
    val width: Int,
    val height: Int
)