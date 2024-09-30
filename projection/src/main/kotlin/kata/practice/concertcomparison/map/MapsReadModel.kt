package kata.practice.concertcomparison.map

import kata.practice.concertcomparison.map.viewmodel.MapsViewModel
import kata.practice.concertcomparison.map.viewmodel.SeatViewModel
import kata.practice.concertcomparison.map.query.GetMaps
import kata.practice.concertcomparison.map.query.GetSeats

interface MapsReadModel {
    fun maps(getMaps: GetMaps): MapsViewModel?
    fun seats(getSeats: GetSeats): List<SeatViewModel>
}