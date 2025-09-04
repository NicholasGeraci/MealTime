package com.example.mealtime.business

import kotlin.math.round

class DegreeMinutesCookingMethod(private val foodItems: List<FoodItem>) {
    init {
        if (foodItems.size < 2) throw IllegalArgumentException(
            "foodItems must be larger than 2. Actual foodItems size is: ${foodItems.size}"
        )
    }

    fun get(): List<FoodItem> {
        val updatedList = mutableListOf<FoodItem>()
        val commonTemperature = foodItems.sumOf { it.temperature } / foodItems.size
        foodItems.forEach {
            val degreeMinutes = it.temperature * it.minutesToCook
            val minutesToCook = degreeMinutes.toDouble() / commonTemperature
            updatedList.add(it.copy(
                temperature = commonTemperature,
                minutesToCook = round(minutesToCook).toInt()
            ))}
        return updatedList.toList()
    }
}