package com.example.myapplceil

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// IMPORTAMOS LOS COLORES UNIFICADOS
import com.example.myapplceil.ui.theme.*

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeTemplateScreen(onBack: () -> Unit = {}) {
    val expenses = listOf(
        Triple("Renta", 3500.0, "🏠"),
        Triple("Internet", 400.0, "🌐"),
        Triple("Luz", 200.0, "💡"),
        Triple("Agua", 100.0, "🚿")
    )

    Scaffold(
        containerColor = NavyDark,
        topBar = { TemplateHeader("🏠 Gastos de Casa", onBack) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text("¿Qué deseas controlar?", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("🏠 Renta", "💡 Luz", "🚿 Agua", "🌐 Internet", "🧹 Limpieza", "🍔 Despensa").forEach { service ->
                        FilterChip(
                            selected = service in listOf("🏠 Renta", "💡 Luz", "🚿 Agua", "🌐 Internet"),
                            onClick = {},
                            label = { Text(service, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = PurpleNeon, 
                                containerColor = CardDark,
                                labelColor = Color.Gray,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }

            item {
                CeilTextField(label = "Nombre del lugar", value = "Departamento CDMX", onValueChange = {}, placeholder = "")
            }

            item {
                Text("Desglose de gastos", color = Color.Gray, fontSize = 14.sp)
                Spacer(Modifier.height(12.dp))
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    expenses.forEach { (name, amount, icon) ->
                        HomeExpenseCard(title = name, amount = amount, icon = icon)
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = CardDark),
                    border = BorderStroke(1.dp, PurpleNeon)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Total mensual", color = Color.White, fontWeight = FontWeight.Bold)
                        Text("$${expenses.sumOf { it.second }.toInt()}", color = PurpleNeon, fontWeight = FontWeight.Bold)
                    }
                }
            }

            item {
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PurpleNeon)
                ) {
                    Text("Configurar gastos de casa", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}
