package kata.practice.concertcomparison.location

import kata.practice.concertcomparison.location.viewmodel.LocationViewModel
import kata.practice.concertcomparison.location.query.GetLocation

interface LocationReadModel {
    fun location(getLocation: GetLocation): LocationViewModel?
}