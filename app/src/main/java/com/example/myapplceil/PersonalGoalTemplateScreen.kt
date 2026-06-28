package com.example.myapplceil

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PersonalGoalTemplateScreen(onBack: () -> Unit = {}) {
    var goalName by remember { mutableStateOf("Nuevo celular") }
    var targetAmount by remember { mutableStateOf("8000") }
    var initialAmount by remember { mutableStateOf("1500") }

    Scaffold(
        containerColor = NavyDark,
        topBar = { TemplateHeader("🚀 Meta Personal", onBack) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text("Define tu propio objetivo", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text("Dale un nombre y establece cuánto quieres juntar.", color = Color.Gray, fontSize = 14.sp)
            }

            item {
                CeilTextField(label = "Nombre de la meta", value = goalName, onValueChange = { goalName = it }, placeholder = "Ej. Viaje de graduación")
                Spacer(Modifier.height(12.dp))
                CeilTextField(label = "Costo objetivo", value = targetAmount, onValueChange = { targetAmount = it }, placeholder = "$", prefix = { Text("$ ", color = Color.White) })
                Spacer(Modifier.height(12.dp))
                CeilTextField(label = "Monto inicial", value = initialAmount, onValueChange = { initialAmount = it }, placeholder = "$", prefix = { Text("$ ", color = Color.White) })
            }

            item {
                Text("Vista previa", color = Color.Gray, fontSize = 14.sp)
                Spacer(Modifier.height(12.dp))
                GoalProgressCard(
                    title = "🚀 $goalName",
                    goalAmount = targetAmount.toDoubleOrNull() ?: 0.0,
                    savedAmount = initialAmount.toDoubleOrNull() ?: 0.0,
                    color = MagentaNeon
                )
            }

            item {
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon)
                ) {
                    Text("Crear meta personal", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}
