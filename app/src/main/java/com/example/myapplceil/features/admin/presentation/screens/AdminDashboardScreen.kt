package com.example.myapplceil.ui.features.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplceil.core.theme.*

@Composable
fun AdminDashboardScreen() {
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
                title = "Panel de Control Admin",
                subtitle = "Visión general del impacto de CEIL"
            )

            // KPI Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.height(400.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                userScrollEnabled = false
            ) {
                item { AdminStatCard("Usuarios Registrados", "1,254", "👥", BlueAdmin) }
                item { AdminStatCard("Usuarios Activos", "892", "🔥", GreenAdmin) }
                item { AdminStatCard("Metas Completadas", "3,420", "🏆", YellowAdmin) }
                item { AdminStatCard("Ahorro Acumulado", "$458K", "💰", PinkAdmin) }
                item { AdminStatCard("Quizzes Respondidos", "5,120", "📝", BlueAdmin) }
                item { AdminStatCard("Racha Promedio", "12 días", "⚡", GreenAdmin) }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Impact Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = CardDark),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Impacto en los Usuarios",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    AdminProgressBar("Usuarios que ahora ahorran", 0.78f, PinkAdmin)
                    AdminProgressBar("Usuarios que registran gastos", 0.92f, BlueAdmin)
                    AdminProgressBar("Usuarios que completaron metas", 0.65f, GreenAdmin)
                    AdminProgressBar("Usuarios que mejoraron sus hábitos", 0.84f, YellowAdmin)
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
