package com.example.currencyrate.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.currencyrate.ui.Destinations.LIST_ROUTE

object Destinations {
    const val LIST_ROUTE = "LIST"
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = LIST_ROUTE
) {

    NavHost(navController = navController, startDestination = startDestination) {

        composable(LIST_ROUTE){}
    }
}