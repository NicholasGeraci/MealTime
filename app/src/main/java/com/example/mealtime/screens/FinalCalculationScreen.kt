package com.example.mealtime.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Oven temperature: ${uiState.foodItems[0].temperature} degrees.",
                modifier = Modifier.padding(12.dp),
                fontSize = 24.sp
            )
            OutlinedCard(modifier = Modifier.width(355.dp).fillMaxHeight()) {
                uiState.foodItems.forEach {
                    Text(
                        text = "${it.identifier}: ${it.minutesToCook} minutes.",
                        modifier = Modifier
                            .padding(3.dp)
                            .offset(x = 8.dp, y = 8.dp),
                        fontSize = 18.sp,
                        maxLines = 1
                    )
                }
            }
        }
    }
}