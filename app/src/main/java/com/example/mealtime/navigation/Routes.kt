package com.example.mealtime.navigation

import kotlinx.serialization.Serializable

object Routes {

    @Serializable
    object HomeScreen

    // String literal until androidx.navigation.compose.navigation supports serializable data types.
    const val CALCULATION_GRAPH = "calculation_graph"

    // String literal until androidx.navigation.compose.navigation supports serializable data types.
    const val INPUT_SCREEN = "input_screen"

    @Serializable
    object FinalCalculationScreen
}