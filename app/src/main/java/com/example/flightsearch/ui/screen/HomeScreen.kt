package com.example.flightsearch.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.data.Airport
import com.example.flightsearch.ui.AppTopBar
import com.example.flightsearch.ui.navigation.NavigationDestination
import com.example.flightsearch.ui.viewModels.AirportViewModel
import com.example.flightsearch.ui.viewModels.AppViewModelProvider

object HomeNavDetails : NavigationDestination {
    override val route: String = "home"

    override val title: String = "Flight search"


}

@Composable
fun HomeScreen(
    onClickAirport: (airportId: Int) -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: AirportViewModel = viewModel(factory = AppViewModelProvider.Factory)

) {
    val homeUiState by homeViewModel.homeUiState.collectAsState()
    Scaffold(
        modifier.fillMaxSize(),
        topBar = { AppTopBar(title = HomeNavDetails.title, canNavigateBack = false) }) {
        AirportSearchList(
            searchedAirports = homeUiState.searchedAirports,
            searchedTerm = homeViewModel.searchTerm,
            modifier = Modifier.padding(it),
            onSearch = { newSearchTerm -> homeViewModel.updateSearchTerm(newSearchTerm) },
            onItemClicked = onClickAirport
        )
    }
}

@Composable
fun AirportSearchList(
    onItemClicked: (itemId: Int) -> Unit,
    searchedAirports: List<Airport>,
    searchedTerm: String,
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}

) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            TextField(
                value = searchedTerm,
                onValueChange = { onSearch(it) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        items(items = searchedAirports, key = { airport -> airport.id }) { airport ->
            Row(modifier = Modifier.clickable { onItemClicked(airport.id) }) {
                Text(
                    text = airport.iataCode,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight(600)
                    )
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = airport.name, modifier = Modifier)
            }
        }
    }

}


