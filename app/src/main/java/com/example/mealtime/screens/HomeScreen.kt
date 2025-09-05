package com.example.mealtime.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onStartClicked: () -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Meal Time") }) },
        bottomBar = { BottomAppBar(containerColor = MaterialTheme.colorScheme.background) {
            Button(
                onClick = { onStartClicked() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(4.dp)
            ) { Text("Get Started") }
        }}
    ) { innerPadding ->
        OutlinedCard(
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.padding(innerPadding)
        ) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxSize().padding(6.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item { Text(
                        textAlign = TextAlign.Center,
                        fontSize = 32.sp,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier,
                        text = "\nWelcome to Meal Time\n"
                    )}
                    item { Text(
                        fontSize = 22.sp,
                        modifier = Modifier.fillMaxWidth(),
                        text = "Introduction:"
                    )}
                    item { Text(
                        fontSize = 16.sp,
                        text = "Meal Time is an Android application that helps you cook two or " +
                                "more frozen foods simultaneously by finding a common cooking " +
                                "temperature and calculating the time to cook each item.\n"
                    )}
                    item { Text(
                        fontSize = 22.sp,
                        modifier = Modifier.fillMaxWidth(),
                        text = "Instructions:"
                    )}
                    item { Text(
                        fontSize = 16.sp,
                        text = "1. Input the quantity of different foods you want to cook " +
                                "simultaneously.\n" +
                                "2. Insert the name, the cooking temperature, and the cooking " +
                                "time (in minutes) for the food you want to cook.\n" +
                                "3. Repeat for all subsequent foods.\n" +
                                "4. Voila! The oven temperature and time to set your timers are " +
                                "presented.\n"
                    )}
                    item { Text(
                        fontSize = 22.sp,
                        modifier = Modifier.fillMaxWidth(),
                        text = "License:"
                    )}
                    item { Text(
                        fontSize = 16.sp,
                        text = "All code is licensed under the GNU General Public Licence 3.0, " +
                                "so feel free to change the code however you like, just make " +
                                "sure you follow the GNU GPL guidelines.\n"
                    )}
                    item { Text(
                        fontSize = 22.sp,
                        modifier = Modifier.fillMaxWidth(),
                        text = "Github:"
                    )}
                    item { Text(
                        fontSize = 16.sp,
                        text = "If you're interested in seeing the code, it's public on my " +
                                "GitHub:\nhttps://GitHub.com/NicholasGeraci\n"
                    )}
                    item { Text(
                        fontSize = 22.sp,
                        modifier = Modifier.fillMaxWidth(),
                        text = "Disclaimer:"
                    )}
                    item { Text(
                        fontSize = 16.sp,
                        text = "I am in no way, shape, and/or form responsible for any " +
                                "burnt/undercooked foods, destroyed appliances, cooked persons, " +
                                "or homes engulfed in flames from the use of this application. " +
                                "This application uses the Degree Cooking Method, which is not " +
                                "without flaw. It assumes all foods require the same heat " +
                                "energy, though that is seldom the case. Adjust the actual " +
                                "cooking times as needed. Use at your own risk!"
                    )}
                }
            }
        }
    }
}