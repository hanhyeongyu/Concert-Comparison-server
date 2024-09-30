package kata.practice.concertcomparison.model

import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import kata.practice.concertcomparison.BaseEntity


@Entity
class Location(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String,

    @Embedded
    var address: Address
): BaseEntity()