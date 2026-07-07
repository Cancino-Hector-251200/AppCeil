package com.example.myapplceil

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.ui.theme.*

data class AdminUserMock(
    val name: String,
    val level: String,
    val streak: Int,
    val savings: Double,
    val status: String,
)

@Composable
fun AdminUsersScreen() {
    val mockUsers = listOf(
        AdminUserMock("Carlos Ruiz", "Oro", 45, 12500.0, "Activo"),
        AdminUserMock("María López", "Platino", 12, 8400.0, "Activo"),
        AdminUserMock("Juan Pérez", "Bronce", 3, 1200.0, "Inactivo"),
        AdminUserMock("Ana García", "Oro", 28, 9800.0, "Activo"),
        AdminUserMock("Roberto Sosa", "Plata", 15, 4500.0, "Activo")
    )

    Scaffold(
        containerColor = NavyDark
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            AdminSectionHeader(
                title = "Gestión de Usuarios",
                subtitle = "Listado de usuarios y su rendimiento"
            )

            Card(
                modifier = Modifier.fillMaxWidth().weight(1f),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CardDark)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Nombre", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.weight(1.5f))
                            Text("Racha", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.weight(0.8f))
                            Text("Ahorro", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.weight(1f))
                            Text("Estado", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.weight(1f))
                        }
                        HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                    }

                    items(mockUsers) { user ->
                        UserTableRow(user)
                    }
                }
            }
        }
    }
}

@Composable
fun UserTableRow(user: AdminUserMock) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1.5f)) {
            Text(text = user.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = user.level, color = MagentaNeon, fontSize = 11.sp)
        }
        Text(text = "🔥 ${user.streak}", color = YellowAdmin, fontSize = 14.sp, modifier = Modifier.weight(0.8f))
        Text(text = "$${user.savings.toInt()}", color = GreenAdmin, fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        
        Surface(
            modifier = Modifier.weight(1f),
            color = if (user.status == "Activo") GreenAdmin.copy(alpha = 0.1f) else Color.White.copy(alpha = 0.05f),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = user.status,
                color = if (user.status == "Activo") GreenAdmin else Color.Gray,
                fontSize = 11.sp,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}
