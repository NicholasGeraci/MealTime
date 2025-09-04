package com.example.mealtime.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mealtime.MealTimeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalCalculationScreen(mealTimeViewModel: MealTimeViewModel, onCloseClicked: () -> Unit) {
    val uiState by mealTimeViewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Meal Time") }) },
        bottomBar = { BottomAppBar(containerColor = MaterialTheme.colorScheme.background) {
            Button(
                onClick = { onCloseClicked() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(4.dp)
            ) { Text("Close") }
        }}
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            //TODO Display the final data.
            item { Text(uiState.foodItems[0].temperature.toString()) }
            itemsIndexed(uiState.foodItems) { _, foodItem ->
                FoodItemCalculation(
                    identifier = foodItem.identifier,
                    timeToCook = foodItem.minutesToCook
                )
            }
        }
    }
}

@Composable
fun FoodItemCalculation(modifier: Modifier = Modifier, identifier: String, timeToCook: Int) {
    OutlinedCard {
        Column {
            Text(identifier)
            Text("Cooking time: $timeToCook")
        }
    }
}