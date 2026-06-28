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

@Composable
fun FoodTemplateScreen(onBack: () -> Unit = {}) {
    var selectedFoodType by remember { mutableStateOf("Universidad") }
    val foodTypes = listOf("🎓 Universidad", "🛒 Supermercado", "🛵 Domicilio", "☕️ Cafetería")

    Scaffold(
        containerColor = NavyDark,
        topBar = { TemplateHeader("🍔 Alimentación", onBack) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text("¿Cómo planeas comer esta semana?", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    foodTypes.forEach { type ->
                        val isSelected = type.contains(selectedFoodType)
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedFoodType = type.split(" ")[1] },
                            label = { Text(type, modifier = Modifier.padding(8.dp)) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = GreenNeon,
                                containerColor = CardDark,
                                labelColor = Color.Gray,
                                selectedLabelColor = Color.White
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            item {
                CeilTextField(
                    label = "Presupuesto semanal para $selectedFoodType",
                    value = "700",
                    onValueChange = {},
                    placeholder = "$",
                    prefix = { Text("$ ", color = Color.White) }
                )
            }

            item {
                Text("Estado actual del presupuesto", color = Color.Gray, fontSize = 14.sp)
                Spacer(Modifier.height(12.dp))
                FoodBudgetCard(
                    title = "Comida en $selectedFoodType",
                    budget = 700.0,
                    spent = 450.0
                )
            }

            item {
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenNeon),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Iniciar control de comida", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}
