package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {

    @Query(
        "SELECT * FROM airport WHERE LOWER(iata_code) LIKE '%' || LOWER(:iata) || '%' " +
                "OR LOWER(name) LIKE '%' || LOWER(:iata) || '%'"
    )
    fun searchForAirport(iata: String): Flow<List<Airport>>

}