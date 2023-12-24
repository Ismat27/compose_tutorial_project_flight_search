package com.example.flightsearch

import android.app.Application
import com.example.flightsearch.data.AppDataContainer
import com.example.flightsearch.data.IAppDataContainer

class FlightSearchApplication: Application() {

    lateinit var container: IAppDataContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}