package com.example.mealtime.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mealtime.MealTimeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalCalculationScreen(mealTimeViewModel: MealTimeViewModel, onCloseClicked: () -> Unit) {
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
        Column(modifier = Modifier.padding(innerPadding)) {
            //TODO Display the final data.
        }
    }
}