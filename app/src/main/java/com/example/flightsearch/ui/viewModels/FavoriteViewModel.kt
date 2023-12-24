package com.example.flightsearch.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.data.FavoriteFlight
import com.example.flightsearch.data.IFavoriteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavoriteViewModel(private val favoriteRepository: IFavoriteRepository) : ViewModel() {

    val favoriteFlights: StateFlow<List<FavoriteFlight>> =
        favoriteRepository.list().map { it }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    suspend fun addFlight(flight: FavoriteFlight) {
        favoriteRepository.add(flight)
    }

}