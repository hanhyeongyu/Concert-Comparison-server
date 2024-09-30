package kata.practice.concertcomparison.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import kata.practice.concertcomparison.BaseEntity
import java.time.LocalDateTime


@Entity
class Performance(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val concertId: Long,

    val locationId: Long,

    val mapId: Long,

    val name: String,

    var performanceAt: LocalDateTime
): BaseEntity()