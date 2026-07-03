package com.example.myapplceil

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.ui.theme.*

data class ApartmentTemplate(
    val icon: String,
    val name: String,
    val route: String
)

data class ApartmentData(
    val id: Int,
    val name: String,
    val icon: ImageVector,
    val budget: Double,
    val spent: Double,
    val color: Color,
    val type: String // "Ahorro", "Entretenimiento", etc.
)

@Composable
fun ApartmentsScreen(
    onBack: () -> Unit = {},
    onNavigateToTemplate: (String) -> Unit = {},
    onSelectApartment: (Int, String) -> Unit = { _, _ -> }
) {
    val templates = listOf(
        ApartmentTemplate("💰", "Ahorro", "template_savings"),
        ApartmentTemplate("🎮", "Entretenimiento", "template_entertainment"),
        ApartmentTemplate("📚", "Proyecto escolar", "template_school"),
        ApartmentTemplate("🍔", "Comida", "template_food"),
        ApartmentTemplate("🏠", "Casa", "template_home"),
        ApartmentTemplate("🚀", "Meta personal", "template_personal")
    )

    // Mock data for my apartments
    val myApartments = listOf(
        ApartmentData(1, "Laptop Gamer", Icons.Default.Laptop, 15000.0, 4500.0, MagentaNeon, "Ahorro"),
        ApartmentData(2, "Proyecto IoT", Icons.Default.Memory, 1200.0, 800.0, PurpleNeon, "Escolar"),
        ApartmentData(3, "Comida semanal", Icons.Default.Restaurant, 700.0, 450.0, YellowNeon, "Comida")
    )

    Scaffold(
        containerColor = NavyDark,
        topBar = {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = Color.White)
                    }
                    Text(text = "Apartados", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
                Text(text = "  Organiza tu dinero", color = Color.Gray, fontSize = 16.sp, modifier = Modifier.padding(start = 12.dp))
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Button(
                    onClick = { onNavigateToTemplate("create_apartment") },
                    modifier = Modifier.fillMaxWidth().height(60.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CardDark)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = MagentaNeon)
                    Spacer(Modifier.width(8.dp))
                    Text("Crear apartado", color = MagentaNeon, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }

            item {
                SectionHeader("Plantillas rápidas")
                Spacer(modifier = Modifier.height(12.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(templates) { template ->
                        TemplateCard(template.icon, template.name, onClick = { onNavigateToTemplate(template.route) })
                    }
                }
            }

            item { SectionHeader("Mis apartados activos") }
            
            items(myApartments) { apartment ->
                ApartmentCard(
                    name = apartment.name,
                    icon = apartment.icon,
                    budget = apartment.budget,
                    spent = apartment.spent,
                    color = apartment.color,
                    onClick = { onSelectApartment(apartment.id, apartment.type) }
                )
            }
            
            item { Spacer(modifier = Modifier.height(32.dp)) }
        }
    }
}
