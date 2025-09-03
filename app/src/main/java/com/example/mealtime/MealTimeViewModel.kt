package com.example.mealtime

import androidx.lifecycle.ViewModel
import com.example.mealtime.business.FoodItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class FoodItemUiState(
    val identifier: String = "",
    val identifierHasError: Boolean = false,

    val temperature: String = "",
    val temperatureHasError: Boolean = false,

    val timeToCook: String = "",
    val timeToCookHasError: Boolean = false,
)

data class MealTimeUiState(
    val foodQuantity: String = "",
    val foodQuantityHasError: Boolean = false,

    val listOfFoodItems: List<FoodItem> = listOf(),
    val listOfFoodItemUiStates: List<FoodItemUiState> = listOf()
)

class MealTimeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MealTimeUiState())
    val uiState: StateFlow<MealTimeUiState> = _uiState.asStateFlow()

    fun setFoodQuantity(newFoodQuantity: String) {
        validateNewFoodQuantity(newFoodQuantity)
        _uiState.update { it.copy(foodQuantity = newFoodQuantity)}
    }

    private fun validateNewFoodQuantity(newFoodQuantity: String) {
        val newListOfFoodItemUiStates = mutableListOf<FoodItemUiState>()
        try {
            val newFoodQuantity = newFoodQuantity.toInt()
            var count = 0
            while (count < newFoodQuantity) {
                newListOfFoodItemUiStates.add(FoodItemUiState())
                count++
            }
            _uiState.update { it.copy(
                listOfFoodItemUiStates = newListOfFoodItemUiStates.toList(),
                foodQuantityHasError = false
            )}
        } catch (_: NumberFormatException) {
            _uiState.update { it.copy(
                listOfFoodItemUiStates = listOf(),
                foodQuantityHasError = true
            )}
        }
    }
}