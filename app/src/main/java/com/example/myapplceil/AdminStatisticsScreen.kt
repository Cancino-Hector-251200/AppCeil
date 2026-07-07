package com.example.myapplceil

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.ui.theme.*

@Composable
fun AdminStatisticsScreen() {
    val scrollState = rememberScrollState()

    Scaffold(
        containerColor = NavyDark
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
                .verticalScroll(scrollState)
        ) {
            AdminSectionHeader(
                title = "Estadísticas Detalladas",
                subtitle = "Análisis de comportamiento semanal"
            )

            // Histogram Card
            AdminChartCard(
                title = "Ahorro Semanal vs Gastos",
                description = "Comparativa de registros de usuarios"
            ) {
                SimulatedHistogram(
                    data = listOf(0.4f, 0.6f, 0.5f, 0.8f, 0.7f, 0.9f, 0.6f),
                    color = PinkAdmin
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Usage by Category
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CardDark)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Apartados más utilizados", color = Color.White, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    val categories = listOf(
                        Triple("Proyecto Escolar", 0.85f, MagentaNeon),
                        Triple("Comida", 0.72f, YellowAdmin),
                        Triple("Ahorro", 0.65f, GreenAdmin),
                        Triple("Entretenimiento", 0.45f, BlueAdmin),
                        Triple("Casa", 0.38f, Color.White),
                        Triple("Meta Personal", 0.30f, PinkAdmin)
                    )

                    categories.forEach { (name, progress, color) ->
                        AdminProgressBar(name, progress, color)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
