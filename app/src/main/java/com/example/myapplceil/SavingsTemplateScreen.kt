package com.example.myapplceil

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.ui.theme.*

@Composable
fun SavingsTemplateScreen(onBack: () -> Unit = {}) {
    var selectedGoalIcon by remember { mutableStateOf("💻") }
    var goalName by remember { mutableStateOf("Laptop Gamer") }
    var targetAmount by remember { mutableStateOf("15000") }
    var initialAmount by remember { mutableStateOf("4500") }
    var deadline by remember { mutableStateOf("20/12/2024") }

    val goals = listOf(
        "💻" to "Laptop", "📱" to "Celular", "🚗" to "Auto", 
        "✈️" to "Viaje", "🛟" to "Fondo", "🎓" to "Universidad", "✏️" to "Otro"
    )

    Scaffold(
        containerColor = NavyDark,
        topBar = { TemplateHeader("💰 Meta de Ahorro", onBack) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text("¿Qué quieres lograr?", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    goals.forEach { (icon, _) ->
                        FilterChip(
                            selected = selectedGoalIcon == icon,
                            onClick = { selectedGoalIcon = icon },
                            label = { Text(icon) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MagentaNeon,
                                containerColor = CardDark,
                                labelColor = Color.White
                            )
                        )
                    }
                }
            }

            item {
                CeilTextField(label = "Nombre de la meta", value = goalName, onValueChange = { goalName = it }, placeholder = "Ej. Mi nueva Laptop")
                Spacer(Modifier.height(12.dp))
                CeilTextField(label = "Monto objetivo", value = targetAmount, onValueChange = { targetAmount = it }, placeholder = "$", prefix = { Text("$ ", color = Color.White) })
                Spacer(Modifier.height(12.dp))
                CeilTextField(
                    label = "Fecha objetivo", 
                    value = deadline, 
                    onValueChange = { deadline = it }, 
                    placeholder = "DD/MM/AAAA",
                    trailingIcon = { Icon(Icons.Default.CalendarToday, null, tint = MagentaNeon) }
                )
                Spacer(Modifier.height(12.dp))
                CeilTextField(label = "Ahorro inicial", value = initialAmount, onValueChange = { initialAmount = it }, placeholder = "$", prefix = { Text("$ ", color = Color.White) })
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MagentaNeon.copy(alpha = 0.05f)),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MagentaNeon.copy(alpha = 0.2f))
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Icon(Icons.Default.CalendarToday, null, tint = MagentaNeon, modifier = Modifier.size(20.dp))
                        Spacer(Modifier.width(12.dp))
                        Text(
                            text = "Basado en tu perfil, ahorras un 15% mensual de tus ingresos. Meta estimada para: $deadline",
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            item {
                Text("Vista previa", color = Color.Gray, fontSize = 14.sp)
                Spacer(Modifier.height(12.dp))
                GoalProgressCard(
                    title = "💰 $goalName",
                    goalAmount = targetAmount.toDoubleOrNull() ?: 0.0,
                    savedAmount = initialAmount.toDoubleOrNull() ?: 0.0,
                    color = MagentaNeon
                )
            }

            item {
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Crear meta de ahorro", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}
