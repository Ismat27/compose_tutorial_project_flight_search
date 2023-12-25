package com.example.flightsearch.ui.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.IFlightRepository
import com.example.flightsearch.ui.screen.FlightScreenNavDetails
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FlightViewModel(
    savedStateHandle: SavedStateHandle,
    private val flightRepository: IFlightRepository
) : ViewModel() {

    private val departureId: Int = checkNotNull(savedStateHandle[FlightScreenNavDetails.routeArg])

    val flightDestinations: StateFlow<List<Airport>> =
        flightRepository.getDestination(departureId).map { it }.stateIn(
            scope = viewModelScope,
            initialValue = emptyList(),
            started = SharingStarted.WhileSubscribed()
        )

}