package com.example.flightsearch.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

interface IAppDataContainer {
    val airportRepository: IAirportRepository
    val favoriteRepository: IFavoriteRepository
    val flightRepository: IFlightRepository
}

class AppDataContainer(context: Context, dataStore: DataStore<Preferences>): IAppDataContainer {
    override val airportRepository: IAirportRepository by lazy {
        AirportRepository(AppDatabase.getDatabase(context).getAirportDao(), dataStore)
    }
    override val favoriteRepository: IFavoriteRepository by lazy {
        FavoriteRepository(AppDatabase.getDatabase(context).getFavoriteDao())
    }

    override val flightRepository: IFlightRepository by lazy {
        FlightRepository(AppDatabase.getDatabase(context).getAirportDao())
    }
}