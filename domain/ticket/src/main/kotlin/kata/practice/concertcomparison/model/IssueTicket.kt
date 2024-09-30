package kata.practice.concertcomparison.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import kata.practice.concertcomparison.BaseEntity

@Entity
class IssueTicket(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val ticketId: Long,

    val userId: Long
): BaseEntity()