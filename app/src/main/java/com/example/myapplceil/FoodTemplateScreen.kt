package com.example.myapplceil

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.ui.theme.*

@Composable
fun FoodTemplateScreen(onBack: () -> Unit = {}) {
    var selectedType by remember { mutableStateOf("Comida universidad") }
    var weeklyBudget by remember { mutableStateOf("") }
    val foodTypes = listOf("Comida universidad", "Supermercado", "Delivery", "Cafetería")

    Scaffold(
        containerColor = NavyDark,
        topBar = { TemplateHeader("🍔 Comida", onBack) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Text("¿Qué tipo de comida es?", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    foodTypes.forEach { type ->
                        FilterChip(
                            selected = selectedType == type,
                            onClick = { selectedType = type },
                            label = { Text(type) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = YellowNeon,
                                containerColor = CardDark,
                                labelColor = Color.White,
                                selectedLabelColor = NavyDark
                            )
                        )
                    }
                }
            }

            item {
                CeilTextField(
                    label = "Presupuesto semanal estimado",
                    value = weeklyBudget,
                    onValueChange = { weeklyBudget = it },
                    placeholder = "$ 0.00",
                    prefix = { Text("$ ", color = Color.White) }
                )
            }

            item {
                Text("Vista previa", color = Color.Gray, fontSize = 14.sp)
                Spacer(Modifier.height(12.dp))
                FoodBudgetCard(
                    title = "🍔 $selectedType",
                    budget = weeklyBudget.toDoubleOrNull() ?: 700.0,
                    spent = 0.0
                )
            }

            item {
                Button(
                    onClick = { /* Save logic */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = YellowNeon),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Crear apartado de comida", color = NavyDark, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }
    }
}
