package com.example.flightsearch.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.data.Airport
import com.example.flightsearch.ui.AppTopBar
import com.example.flightsearch.ui.navigation.NavigationDestination
import com.example.flightsearch.ui.viewModels.AppViewModelProvider
import com.example.flightsearch.ui.viewModels.FlightViewModel

object FlightScreenNavDetails : NavigationDestination {
    override val route: String = "flights"
    override val title: String = "Flights"
    const val routeArg: String = "departureId"
    val routeWithArg: String = "$route/{$routeArg}"
}

@Composable
fun FlightScreen(modifier: Modifier = Modifier, onNavigateUp: () -> Unit) {
    val viewModel: FlightViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val destinations by viewModel.flightDestinations.collectAsState()
    Scaffold(
        modifier,
        topBar = {
            AppTopBar(
                title = FlightScreenNavDetails.title,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }) {
        LazyColumn(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp)
        ) {
            items(items = destinations, key = { destination -> destination.id }) { destination ->
                DestinationCard(destination = destination)
            }
        }
    }

}

@Composable
fun DestinationCard(destination: Airport, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = destination.iataCode,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = destination.name, style = MaterialTheme.typography.bodyLarge)
        }
    }
}