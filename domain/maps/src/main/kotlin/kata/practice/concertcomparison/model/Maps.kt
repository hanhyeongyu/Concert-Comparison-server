package kata.practice.concertcomparison.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import kata.practice.concertcomparison.BaseEntity

@Entity
class Maps(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val locationId: Long,

    var width: Int,

    var height: Int
): BaseEntity()