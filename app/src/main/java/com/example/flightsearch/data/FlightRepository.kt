package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

interface IFlightRepository {
    fun getDestination(departureId: Int): Flow<List<Airport>>
}

class FlightRepository(private val airportDao: AirportDao): IFlightRepository {
    override fun getDestination(departureId: Int): Flow<List<Airport>> {
        return airportDao.getDestination(departureId)
    }
}