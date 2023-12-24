package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

interface IAirportRepository{
    fun search(iata: String): Flow<List<Airport>>
}

class AirportRepository(private val airportDao: AirportDao): IAirportRepository {
    override fun search(iata: String): Flow<List<Airport>> {
        return airportDao.searchForAirport(iata)
    }
}