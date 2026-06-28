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
import com.example.myapplceil.ui.theme.*

@Composable
fun SchoolProjectTemplateScreen(onBack: () -> Unit = {}) {
    var projectName by remember { mutableStateOf("Proyecto IoT") }
    var budget by remember { mutableStateOf("1200") }
    val categories = listOf(
        Triple("📄 Impresiones", 100.0, MagentaNeon),
        Triple("🧪 Materiales", 500.0, YellowNeon),
        Triple("🚍 Transporte", 200.0, PurpleNeon),
        Triple("💻 Software", 0.0, GreenNeon),
        Triple("🍔 Comidas", 0.0, Color.Cyan)
    )

    Scaffold(
        containerColor = NavyDark,
        topBar = { TemplateHeader("📚 Proyecto Escolar", onBack) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text("¿Qué tipo de trabajo es?", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("Proyecto", "Materia", "Tesis").forEach { type ->
                        FilterChip(
                            selected = type == "Proyecto",
                            onClick = {},
                            label = { Text(type) },
                            colors = FilterChipDefaults.filterChipColors(selectedContainerColor = MagentaNeon, containerColor = CardDark)
                        )
                    }
                }
            }

            item {
                CeilTextField(label = "Nombre del proyecto", value = projectName, onValueChange = { projectName = it }, placeholder = "")
                Spacer(Modifier.height(12.dp))
                CeilTextField(label = "Presupuesto total estimado", value = budget, onValueChange = { budget = it }, placeholder = "$")
            }

            item {
                Text("Sugerencia de gastos", color = Color.Gray, fontSize = 14.sp)
                Spacer(Modifier.height(12.dp))
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    categories.forEach { (name, amount, color) ->
                        SchoolBudgetCard(category = name, amount = amount, color = color)
                    }
                }
            }

            item {
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon)
                ) {
                    Text("Crear presupuesto escolar")
                }
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}
