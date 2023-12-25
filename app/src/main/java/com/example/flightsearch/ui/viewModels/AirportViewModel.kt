package com.example.flightsearch.ui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.IAirportRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AirportViewModel(private val airportRepository: IAirportRepository) : ViewModel() {

    var searchTerm by mutableStateOf("")
        private set

    val homeUiState: StateFlow<HomeUiState> = airportRepository.searchTerm
        .map {
            val airports = if (it.isBlank()) {
                emptyList()
            } else {
                getSearchedAirport(it)
            }
            HomeUiState(searchTerm = it, searchedAirports = airports)
        }
        .stateIn(
            initialValue = HomeUiState(),
            started = SharingStarted.WhileSubscribed(5_000),
            scope = viewModelScope
        )

    private suspend fun getSearchedAirport(searchTerm: String): List<Airport> {
        return airportRepository.search(searchTerm).filterNotNull().first()
    }

    fun updateSearchTerm(newSearchTerm: String) {
        searchTerm = newSearchTerm
        viewModelScope.launch {
            airportRepository.updateSearchTerm(searchTerm)
        }
    }

    init {
        viewModelScope.launch {
            val storedSearchTerm:String = airportRepository.searchTerm.first()
            searchTerm = storedSearchTerm
        }
    }

}

data class HomeUiState(
    val searchTerm: String = "",
    val searchedAirports: List<Airport> = emptyList()
)