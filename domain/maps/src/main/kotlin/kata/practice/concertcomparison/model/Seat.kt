package kata.practice.concertcomparison.model

import jakarta.persistence.*
import kata.practice.concertcomparison.BaseEntity

@Entity
class Seat(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val mapId: Long,

    val seatInfo: String,

    val rowNum: Int,

    val seatNum: Int,

    val posTop: Int,

    val posLeft: Int
): BaseEntity(){


    enum class Type{
        NORMAL
    }
}