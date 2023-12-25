package com.example.flightsearch.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flightsearch.ui.screen.FlightScreen
import com.example.flightsearch.ui.screen.FlightScreenNavDetails
import com.example.flightsearch.ui.screen.HomeNavDetails
import com.example.flightsearch.ui.screen.HomeScreen

interface NavigationDestination {
    val route: String
    val title: String
}

@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeNavDetails.route, modifier) {
        composable(route = HomeNavDetails.route) {
            HomeScreen(onClickAirport = { navController.navigate("${FlightScreenNavDetails.route}/$it") })
        }
        composable(
            route = FlightScreenNavDetails.routeWithArg,
            arguments = listOf(navArgument(FlightScreenNavDetails.routeArg) {
                type = NavType.IntType
            })
        ) {
            FlightScreen(onNavigateUp = { navController.navigateUp() })
        }
    }
}