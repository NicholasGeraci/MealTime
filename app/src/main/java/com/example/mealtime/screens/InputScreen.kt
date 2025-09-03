package com.example.mealtime.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mealtime.MealTimeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(mealTimeViewModel: MealTimeViewModel, onDoneClicked: () -> Unit) {
    val uiState by mealTimeViewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Meal Time") }) },
        bottomBar = { BottomAppBar(containerColor = MaterialTheme.colorScheme.background) {
            Button(
                onClick = { onDoneClicked() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(4.dp)
            ) { Text("Done") }
        }}
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FoodQuantityInputField(
                foodQuantity = uiState.foodQuantity,
                onFoodQuantityChange = { mealTimeViewModel.setFoodQuantity(it) }
            )
            LazyColumn {
                items(uiState.listOfFoodItemUiStates.size) {
                    FoodDataInputItem()
                }
            }
        }
    }
}

@Composable
fun FoodQuantityInputField(
    modifier: Modifier = Modifier,
    foodQuantity: String,
    onFoodQuantityChange: (String) -> Unit
) {
    //TODO Expand this feature.
    OutlinedTextField(
        value = foodQuantity,
        onValueChange = { onFoodQuantityChange(it) },
        modifier = modifier
    )
}

@Composable
fun FoodDataInputItem(modifier: Modifier = Modifier) {
    //TODO Implement this.
    Text("test")
}