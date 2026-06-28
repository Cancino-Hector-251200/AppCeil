package com.example.myapplceil

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun SavingsTemplateScreen(onBack: () -> Unit = {}) {
    var selectedGoalIcon by remember { mutableStateOf("💻") }
    val goals = listOf(
        "💻" to "Laptop", "📱" to "Celular", "🚗" to "Auto", 
        "✈️" to "Viaje", "🛟" to "Fondo", "🎓" to "Universidad"
    )

    Scaffold(
        containerColor = NavyDark,
        topBar = { TemplateHeader("💰 Nueva Meta", onBack) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text("¿Qué deseas lograr?", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    goals.forEach { (icon, label) ->
                        FilterChip(
                            selected = selectedGoalIcon == icon,
                            onClick = { selectedGoalIcon = icon },
                            label = { Text(icon) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MagentaNeon,
                                containerColor = CardDark
                            )
                        )
                    }
                }
            }

            item {
                CeilTextField(label = "Nombre de la meta", value = "Laptop Gamer", onValueChange = {}, placeholder = "")
                Spacer(Modifier.height(12.dp))
                CeilTextField(label = "Monto objetivo", value = "15000", onValueChange = {}, placeholder = "$")
                Spacer(Modifier.height(12.dp))
                CeilTextField(label = "Ahorro inicial", value = "4500", onValueChange = {}, placeholder = "$")
            }

            item {
                Text("Vista previa", color = Color.Gray, fontSize = 14.sp)
                Spacer(Modifier.height(12.dp))
                GoalProgressCard(
                    title = "$selectedGoalIcon Laptop Gamer",
                    goalAmount = 15000.0,
                    savedAmount = 4500.0,
                    color = MagentaNeon
                )
            }

            item {
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon)
                ) {
                    Text("Crear meta de ahorro")
                }
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}
