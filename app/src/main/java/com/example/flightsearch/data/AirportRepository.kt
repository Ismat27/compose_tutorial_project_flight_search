package com.example.flightsearch.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

interface IAirportRepository {
    fun search(iata: String): Flow<List<Airport>>
    val searchTerm: Flow<String>
    suspend fun updateSearchTerm(searchTerm: String)
}

class AirportRepository(
    private val airportDao: AirportDao,
    private val dataStore: DataStore<Preferences>
) : IAirportRepository {

    override val searchTerm: Flow<String> = dataStore.data.catch {
        if (it is IOException) {
            Log.d("AirportRepository:searchTerm", it.message ?: "error reading search term")
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map { preferences -> preferences[SEARCH_TERM] ?: "" }

    override suspend fun updateSearchTerm(searchTerm: String) {
        dataStore.edit { preferences ->
            preferences[SEARCH_TERM] = searchTerm
        }
    }

    override fun search(iata: String): Flow<List<Airport>> {
        return airportDao.searchForAirport(iata)
    }

    companion object {
        private val SEARCH_TERM = stringPreferencesKey("search_term")
    }
}