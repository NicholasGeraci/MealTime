package com.example.mealtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.example.mealtime.navigation.MealTimeNavigation
import com.example.mealtime.ui.theme.MealTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MealTimeTheme {
                Surface {
                    MealTimeNavigation()
                }
            }
        }
    }
}
