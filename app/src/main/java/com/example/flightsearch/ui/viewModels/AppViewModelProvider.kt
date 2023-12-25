package com.example.flightsearch.ui.viewModels

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightSearchApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            AirportViewModel(flightSearchApplication().container.airportRepository)
        }

        initializer {
            FavoriteViewModel(flightSearchApplication().container.favoriteRepository)
        }

        initializer {
            FlightViewModel(
                this.createSavedStateHandle(),
                flightSearchApplication().container.flightRepository
            )
        }
    }
}

fun CreationExtras.flightSearchApplication(): FlightSearchApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightSearchApplication)