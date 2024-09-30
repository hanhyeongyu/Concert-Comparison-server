package kata.practice.concertcomparison.model

import jakarta.persistence.*
import kata.practice.concertcomparison.BaseEntity
import kata.practice.concertcomparison.common.exception.BaseException
import kata.practice.concertcomparison.common.exception.ErrorCode.ALREADY_CHOSE_TICKETS
import kata.practice.concertcomparison.common.exception.IllegalStatusException
import kata.practice.concertcomparison.model.Ticket.Status.*


@Entity
class Ticket(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var performanceId: Long,

    val mapId: Long,

    val seatId: Long,

    @Enumerated(EnumType.STRING)
    private var status: Status = IDLE,

    private var userId: Long? = null
): BaseEntity(){


    fun select(userId: Long){
        if (status != IDLE){
            throw BaseException(ALREADY_CHOSE_TICKETS)
        }
        this.status = SELECTED
        this.userId = userId
    }


    fun issue(userId: Long): IssueTicket {
        if (status != SELECTED && this.userId != userId){
            throw IllegalStatusException()
        }
        this.status = ISSUED
        return IssueTicket(
            ticketId = this.id!!,
            userId =  userId
        )
    }

    fun timeOut(){
        if (status != SELECTED){
             throw IllegalStatusException()
        }
        this.status = IDLE
        this.userId = null
    }

    fun cancel(){
        if (status != ISSUED){
            throw IllegalStatusException()
        }
        this.status = IDLE
        this.userId = null
    }

    fun enable(): Boolean{
        return this.status == IDLE
    }


    enum class Status {
        IDLE,
        SELECTED,
        ISSUED
    }

}