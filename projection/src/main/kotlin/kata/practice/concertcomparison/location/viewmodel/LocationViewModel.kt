package kata.practice.concertcomparison.location.viewmodel

import kata.practice.concertcomparison.model.Address
import kata.practice.concertcomparison.model.Location

data class LocationViewModel(
    val id: Long,
    val name: String,
    val address: AddressViewModel
){
    constructor(location: Location) : this(
        id = location.id!!,
        name = location.name,
        address = AddressViewModel(location.address)
    )
}

data class AddressViewModel(
    val city: String,
    val street: String,
    val zipCode: String
){
    constructor(address: Address): this(
        city = address.city,
        street = address.street,
        zipCode = address.zipCode
    )
}
