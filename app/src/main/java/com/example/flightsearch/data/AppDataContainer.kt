package com.example.flightsearch.data

import android.content.Context

interface IAppDataContainer {
    val airportRepository: IAirportRepository
    val favoriteRepository: IFavoriteRepository
}

class AppDataContainer(context: Context): IAppDataContainer {
    override val airportRepository: IAirportRepository by lazy {
        AirportRepository(AppDatabase.getDatabase(context).getAirportDao())
    }
    override val favoriteRepository: IFavoriteRepository by lazy {
        FavoriteRepository(AppDatabase.getDatabase(context).getFavoriteDao())
    }
}