package com.example.flightsearch

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.flightsearch.data.AppDataContainer
import com.example.flightsearch.data.IAppDataContainer

class FlightSearchApplication: Application() {

    lateinit var container: IAppDataContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this, datastore)
    }
}

private const val APP_PREFERENCE_NAME = "APP_PREFERENCES"

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(APP_PREFERENCE_NAME)