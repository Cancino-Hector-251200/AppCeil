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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// IMPORTANTE: Importamos los colores desde el nuevo paquete theme
import com.example.myapplceil.ui.theme.*

data class ApartmentTemplate(
    val icon: String,
    val name: String,
    val route: String
)

data class Apartment(
    val id: Int,
    val name: String,
    val icon: ImageVector,
    val budget: Double,
    val spent: Double,
    val color: Color
)

@Composable
fun ApartmentsScreen(
    onBack: () -> Unit = {},
    onNavigateToTemplate: (String) -> Unit = {},
    onSelectApartment: (Int) -> Unit = {}
) {
    val templates = listOf(
        ApartmentTemplate("💰", "Ahorro", "template_savings"),
        ApartmentTemplate("🎮", "Entretenimiento", "template_entertainment"),
        ApartmentTemplate("📚", "Escolar", "template_school"),
        ApartmentTemplate("🏠", "Casa", "template_home"),
        ApartmentTemplate("🍔", "Comida", "template_food"),
        ApartmentTemplate("🚀", "Meta", "template_personal")
    )

    val myApartments = listOf(
        Apartment(1, "Laptop Gamer", Icons.Default.Laptop, 15000.0, 4500.0, MagentaNeon),
        Apartment(2, "Proyecto IoT", Icons.Default.Memory, 1200.0, 800.0, PurpleNeon),
        Apartment(3, "Comida semanal", Icons.Default.Restaurant, 700.0, 450.0, YellowNeon)
    )

    Scaffold(
        containerColor = NavyDark,
        topBar = {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = Color.White)
                    }
                    Text(text = "📁 Apartados", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
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
                    onClick = { onNavigateToTemplate("template_personal") },
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
                        TemplateCard(template, onClick = { onNavigateToTemplate(template.route) })
                    }
                }
            }

            item { SectionHeader("Mis apartados activos") }
            items(myApartments) { apartment ->
                ApartmentCard(apartment, onClick = { onSelectApartment(apartment.id) })
            }
            
            item { Spacer(modifier = Modifier.height(32.dp)) }
        }
    }
}

@Composable
fun TemplateCard(template: ApartmentTemplate, onClick: () -> Unit) {
    Card(
        modifier = Modifier.width(130.dp).height(150.dp).clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.size(50.dp).background(MagentaNeon.copy(alpha = 0.1f), RoundedCornerShape(15.dp)), contentAlignment = Alignment.Center) {
                Text(template.icon, fontSize = 28.sp)
            }
            Spacer(Modifier.height(12.dp))
            Text(template.name, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ApartmentCard(apartment: Apartment, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(apartment.icon, contentDescription = null, tint = apartment.color, modifier = Modifier.size(24.dp))
                Spacer(Modifier.width(12.dp))
                Text(apartment.name, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(16.dp))
            val progress = (apartment.spent / apartment.budget).toFloat()
            ProgressBar(progress, apartment.color)
            Spacer(Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Gastado: $${apartment.spent.toInt()}", color = Color.Gray, fontSize = 12.sp)
                Text("Meta: $${apartment.budget.toInt()}", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(text = title, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
}
