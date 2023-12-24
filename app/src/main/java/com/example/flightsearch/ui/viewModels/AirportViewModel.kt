package com.example.flightsearch.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.IAirportRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AirportViewModel(private val airportRepository: IAirportRepository) : ViewModel() {

    val airportSearchResult: StateFlow<List<Airport>> = airportRepository.search("INT").map {
        it
    }.stateIn(
        initialValue = emptyList(),
        started = SharingStarted.WhileSubscribed(),
        scope = viewModelScope
    )

}