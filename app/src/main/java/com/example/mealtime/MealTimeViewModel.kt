package com.example.mealtime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealtime.business.DegreeMinutesCookingMethod
import com.example.mealtime.business.FoodItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class FoodItemUiState(
    val identifier: String = "",
    val identifierHasError: Boolean = false,

    val temperature: String = "",
    val temperatureHasError: Boolean = false,

    val minutesToCook: String = "",
    val minutesToCookHasError: Boolean = false,
)

data class MealTimeUiState(
    val foodQuantity: String = "",
    val foodQuantityHasError: Boolean = false,

    val foodItems: List<FoodItem> = listOf(),
    val foodItemUiStates: List<FoodItemUiState> = listOf(),

    val doneButtonEnabled: Boolean = false
)

class MealTimeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MealTimeUiState())
    val uiState: StateFlow<MealTimeUiState> = _uiState.asStateFlow()

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage: SharedFlow<String> = _toastMessage.asSharedFlow()

    private fun emitToast(text: String) {
        viewModelScope.launch { _toastMessage.emit(text) }
    }

    fun setFoodQuantity(newFoodQuantity: String) {
        validateNewFoodQuantity(newFoodQuantity)
        _uiState.update { it.copy(foodQuantity = newFoodQuantity)}
    }

    private fun validateNewFoodQuantity(newFoodQuantity: String) {
        try {
            val newFoodQuantity = newFoodQuantity.toInt()
            when {
                newFoodQuantity < 2 -> {
                    emitToast("Minimum of two foods.")
                    _uiState.update { it.copy(
                            foodItemUiStates = listOf(),
                            foodQuantityHasError = true
                    )}
                }
                newFoodQuantity > 5 -> {
                    emitToast("You're gonna burn your house down.")
                    _uiState.update { it.copy(
                            foodItemUiStates = listOf(),
                            foodQuantityHasError = true
                    )}
                }
                else -> {
                    val newListOfFoodItemUiStates = mutableListOf<FoodItemUiState>()
                    var count = 0
                    while (count < newFoodQuantity) {
                        newListOfFoodItemUiStates.add(FoodItemUiState())
                        count++
                    }
                    _uiState.update { it.copy(
                            foodItemUiStates = newListOfFoodItemUiStates.toList(),
                            foodQuantityHasError = false
                    )}
                }
            }
        } catch (_: NumberFormatException) {
            if (newFoodQuantity.isNotBlank()) emitToast("Invalid input.")
            _uiState.update { it.copy(
                foodItemUiStates = listOf(),
                foodQuantityHasError = true
            )}
        }
    }

    fun setFoodItemIdentifier(index: Int, newIdentifier: String) {
        _uiState.update {
            val updatedList = it.foodItemUiStates.toMutableList()
            updatedList[index] = updatedList[index].copy(
                identifier = newIdentifier,
                identifierHasError = newIdentifier.isBlank()
            )
            it.copy(foodItemUiStates = updatedList)
        }
        enableDoneButton()
    }

    fun setFoodItemTemperature(index: Int, newTemperature: String) {
        val hasError = newTemperature.toIntOrNull() == null && newTemperature.isNotBlank()
        if (hasError) emitToast("Invalid Input.")
        _uiState.update {
            val updatedList = it.foodItemUiStates.toMutableList()
            updatedList[index] = updatedList[index].copy(
                temperature = newTemperature,
                temperatureHasError = hasError || newTemperature.isBlank()
            )
            it.copy(foodItemUiStates = updatedList)
        }
        enableDoneButton()
    }

    fun setFoodItemMinutesToCook(index: Int, newTimeToCook: String) {
        val hasError = newTimeToCook.toIntOrNull() == null && newTimeToCook.isNotBlank()
        if (hasError) emitToast("Invalid Input.")
        _uiState.update {
            val updatedList = it.foodItemUiStates.toMutableList()
            updatedList[index] = updatedList[index].copy(
                minutesToCook = newTimeToCook,
                minutesToCookHasError = hasError || newTimeToCook.isBlank()
            )
            it.copy(foodItemUiStates = updatedList)
        }
        enableDoneButton()
    }

    private fun enableDoneButton() {
        _uiState.update { currentState ->
            val hasError = mutableListOf<Boolean>()
            hasError.add(currentState.foodQuantityHasError && currentState.foodQuantity.isBlank())
            currentState.foodItemUiStates.forEach {
                hasError.add(it.identifierHasError || it.identifier.isBlank())
                hasError.add(it.temperatureHasError || it.temperature.isBlank())
                hasError.add(it.minutesToCookHasError || it.minutesToCook.isBlank())
            }
            currentState.copy(doneButtonEnabled = hasError.all { !it })
        }
    }

    fun updateListOfFoodItems() {
        _uiState.update { currentState ->
            val updatedList = currentState.foodItems.toMutableList()
            currentState.foodItemUiStates.forEach {
                updatedList.add(FoodItem(
                    identifier = it.identifier,
                    temperature = it.temperature.toInt(),
                    minutesToCook = it.minutesToCook.toInt()
                ))
            }
            currentState.copy(foodItems = DegreeMinutesCookingMethod(updatedList).get())
        }
    }
}