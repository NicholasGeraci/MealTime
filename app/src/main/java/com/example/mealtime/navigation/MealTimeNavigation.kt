package com.example.mealtime.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.mealtime.MealTimeViewModel
import com.example.mealtime.screens.FinalCalculationScreen
import com.example.mealtime.screens.HomeScreen
import com.example.mealtime.screens.InputScreen

@Composable
fun MealTimeNavigation() {
    val navController = rememberNavController()

    // Combination of string literals and serializable data types until
    // androidx.navigation.compose.navigation adds full serializable data type support.
    NavHost(navController, startDestination = Routes.HomeScreen) {
        composable<Routes.HomeScreen> { HomeScreen(
            onStartClicked = { navController.navigate(Routes.CALCULATION_GRAPH) }
        )}

        navigation(startDestination = Routes.INPUT_SCREEN, route = Routes.CALCULATION_GRAPH) {
            composable(route = Routes.INPUT_SCREEN) {
                val calcGraphEntry = remember(it) {
                    navController.getBackStackEntry(Routes.CALCULATION_GRAPH)
                }
                val mealTimeViewModel: MealTimeViewModel = viewModel(
                    viewModelStoreOwner = calcGraphEntry
                )
                InputScreen(
                    mealTimeViewModel = mealTimeViewModel,
                    onDoneClicked = { navController.navigate(Routes.FinalCalculationScreen) }
                )
            }

            composable<Routes.FinalCalculationScreen> {
                val calcGraphEntry = remember(it) {
                    navController.getBackStackEntry(Routes.CALCULATION_GRAPH)
                }
                val mealTimeViewModel: MealTimeViewModel = viewModel(
                    viewModelStoreOwner = calcGraphEntry
                )
                FinalCalculationScreen(
                    mealTimeViewModel = mealTimeViewModel,
                    onCloseClicked = { navController.navigate(Routes.HomeScreen) }
                )
            }
        }
    }
}