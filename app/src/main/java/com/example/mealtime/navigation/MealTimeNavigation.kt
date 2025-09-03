package com.example.mealtime.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mealtime.MealTimeViewModel
import com.example.mealtime.screens.FinalCalculationScreen
import com.example.mealtime.screens.HomeScreen
import com.example.mealtime.screens.InputScreen

@Composable
fun MealTimeNavigation() {
    val navController = rememberNavController()
    val mealTimeViewModel: MealTimeViewModel = viewModel()

    NavHost(navController, startDestination = Routes.HomeScreen) {
        composable<Routes.HomeScreen> { HomeScreen(
            onStartClicked = { navController.navigate(Routes.InputScreen) }
        )}

        composable<Routes.InputScreen> { InputScreen(
            mealTimeViewModel = mealTimeViewModel,
            onDoneClicked = { navController.navigate(Routes.FinalCalculationScreen) }
        )}

        composable<Routes.FinalCalculationScreen> { FinalCalculationScreen(
            mealTimeViewModel = mealTimeViewModel,
            onCloseClicked = { navController.navigate(Routes.HomeScreen) }
        )}
    }
}