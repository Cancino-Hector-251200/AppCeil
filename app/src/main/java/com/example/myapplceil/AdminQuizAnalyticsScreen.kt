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
fun AdminQuizAnalyticsScreen() {
    val scrollState = rememberScrollState()

    Scaffold(
        containerColor = NavyDark,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
                .verticalScroll(scrollState)
        ) {
            AdminSectionHeader(
                title = "Educación Financiera",
                subtitle = "Analítica de cuestionarios y aprendizaje"
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                AdminStatCard("Promedio Aciertos", "88%", "📊", GreenAdmin, Modifier.weight(1f))
                AdminStatCard("Tasa Completitud", "94%", "✅", BlueAdmin, Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CardDark)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Top Preguntas", color = Color.White, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    QuizQuestionStat("Pregunta más acertada", "¿Qué es el ahorro?", "98%", GreenAdmin)
                    QuizQuestionStat("Pregunta más fallada", "¿Cómo funciona el interés compuesto?", "42%", PinkAdmin)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            AdminChartCard(
                title = "Cuestionarios respondidos",
                description = "Progreso mensual de participación"
            ) {
                SimulatedHistogram(
                    data = listOf(0.3f, 0.4f, 0.6f, 0.8f, 0.9f, 0.7f, 0.5f),
                    color = YellowAdmin
                )
            }
        }
    }
}

@Composable
fun QuizQuestionStat(label: String, question: String, percentage: String, color: Color) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = label, color = Color.Gray, fontSize = 12.sp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = question, color = Color.White, fontSize = 14.sp, modifier = Modifier.weight(1f))
            Text(text = percentage, color = color, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        HorizontalDivider(modifier = Modifier.padding(top = 8.dp), color = Color.White.copy(alpha = 0.05f))
    }
}
