package com.example.mealtime.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mealtime.MealTimeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(mealTimeViewModel: MealTimeViewModel, onDoneClicked: () -> Unit) {
    val uiState by mealTimeViewModel.uiState.collectAsState()

    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        mealTimeViewModel.toastMessage.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

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
                foodQuantityHasError = uiState.foodQuantityHasError,
                onFoodQuantityChange = { mealTimeViewModel.setFoodQuantity(it) }
            )
            LazyColumn {
                itemsIndexed(uiState.listOfFoodItemUiStates) { i, foodItemState ->
                    FoodDataInputItem(
                        identifier = foodItemState.identifier,
                        identifierHasError = foodItemState.identifierHasError,
                        onIdentifierChange = { mealTimeViewModel.setFoodItemIdentifier(
                            index = i,
                            newIdentifier = it
                        )},
                        temperature = foodItemState.temperature,
                        temperatureHasError = foodItemState.temperatureHasError,
                        onTemperatureChange = { mealTimeViewModel.setFoodItemTemperature(
                            index = i,
                            newTemperature = it
                        )},
                        timeToCook = foodItemState.timeToCook,
                        timeToCookHasError = foodItemState.timeToCookHasError,
                        onTimeToCookChange = { mealTimeViewModel.setFoodItemTimeToCook(
                            index = i,
                            newTimeToCook = it
                        )}
                    )
                }
            }
        }
    }
}

@Composable
private fun FoodQuantityInputField(
    modifier: Modifier = Modifier,
    foodQuantity: String,
    foodQuantityHasError: Boolean,
    onFoodQuantityChange: (String) -> Unit
) {
    NumbersOnlyTextField(
        modifier = modifier,
        value = foodQuantity,
        onValueChange = { onFoodQuantityChange(it) },
        isError = foodQuantityHasError
    )
}

@Composable
private fun FoodDataInputItem(
    modifier: Modifier = Modifier,
    identifier: String,
    identifierHasError: Boolean,
    onIdentifierChange: (String) -> Unit,
    temperature: String,
    temperatureHasError: Boolean,
    onTemperatureChange: (String) -> Unit,
    timeToCook: String,
    timeToCookHasError: Boolean,
    onTimeToCookChange: (String) -> Unit
) {
    //TODO Make this pretty.
    OutlinedCard(modifier = modifier) {
        NumbersOnlyTextField(
            value = identifier,
            onValueChange = { onIdentifierChange(it) },
            isError = identifierHasError
        )
        NumbersOnlyTextField(
            value = temperature,
            onValueChange = { onTemperatureChange(it) },
            isError = temperatureHasError
        )
        NumbersOnlyTextField(
            value = timeToCook,
            onValueChange = { onTimeToCookChange(it) },
            isError = timeToCookHasError
        )
    }
}

@Composable
private fun NumbersOnlyTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        isError = isError,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )
}