package com.example.myapplceil

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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

// Colores extra para Apartados
val RedNeon = Color(0xFFEF4444)

data class ApartmentTemplate(
    val icon: String,
    val name: String,
    val description: String,
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
    onCreateNew: () -> Unit = {},
    onSelectApartment: (Int) -> Unit = {},
    onNavigateToTemplate: (String) -> Unit = {} // Navegación dinámica por ruta
) {
    val templates = listOf(
        ApartmentTemplate("📚", "Proyecto escolar", "Materiales y transporte", "template_school"),
        ApartmentTemplate("🏠", "Casa", "Renta y servicios", "template_home"),
        ApartmentTemplate("💰", "Ahorro", "Metas de ahorro", "template_savings"),
        ApartmentTemplate("🎮", "Entretenimiento", "Streaming y juegos", "template_entertainment"),
        ApartmentTemplate("🍔", "Comida", "Dieta y universidad", "template_food"),
        ApartmentTemplate("🚀", "Meta Personal", "Cualquier objetivo", "template_personal")
    )

    val myApartments = listOf(
        Apartment(1, "Proyecto final", Icons.Default.MenuBook, 1000.0, 350.0, PurpleNeon),
        Apartment(2, "Comida semana", Icons.Default.Restaurant, 500.0, 420.0, YellowNeon),
        Apartment(3, "Viaje", Icons.Default.Flight, 2000.0, 1500.0, GreenNeon)
    )

    Scaffold(
        containerColor = NavyDark,
        topBar = {
            ApartmentsHeader(onBack = onBack, onCreate = onCreateNew)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Text(
                    text = "Organiza tu dinero por objetivos",
                    color = Color.LightGray,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            item {
                CreateApartmentButton(onClick = { onNavigateToTemplate("template_personal") })
            }

            item {
                SectionHeader("Planear con plantillas")
                Spacer(modifier = Modifier.height(12.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(templates) { template ->
                        TemplateCard(
                            template = template,
                            onClick = { onNavigateToTemplate(template.route) }
                        )
                    }
                }
            }

            item {
                SectionHeader("Mis apartados activos")
            }

            items(myApartments) { apartment ->
                ApartmentCard(
                    apartment = apartment,
                    onClick = { onSelectApartment(apartment.id) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ApartmentsHeader(onBack: () -> Unit, onCreate: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = Color.White)
            }
            Text(
                text = "Apartados",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        IconButton(
            onClick = onCreate,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(CardDark)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Añadir", tint = MagentaNeon)
        }
    }
}

@Composable
fun CreateApartmentButton(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark),
        border = androidx.compose.foundation.BorderStroke(1.dp, MagentaNeon.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(Icons.Default.AddCircle, contentDescription = null, tint = MagentaNeon, modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Crear meta personalizada",
                color = MagentaNeon,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TemplateCard(template: ApartmentTemplate, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(180.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MagentaNeon.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = template.icon, fontSize = 24.sp)
            }
            Column {
                Text(
                    text = template.name,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = template.description,
                    color = Color.Gray,
                    fontSize = 11.sp,
                    lineHeight = 14.sp
                )
            }
        }
    }
}

@Composable
fun ApartmentCard(apartment: Apartment, onClick: () -> Unit) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(600)) + slideInVertically(initialOffsetY = { it / 2 })
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = CardDark)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(apartment.color.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(apartment.icon, contentDescription = null, tint = apartment.color)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = apartment.name,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row {
                        IconButton(onClick = { /* Edit */ }) {
                            Icon(Icons.Default.Edit, contentDescription = "Editar", tint = Color.Gray, modifier = Modifier.size(20.dp))
                        }
                        IconButton(onClick = { /* Delete */ }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = RedNeon, modifier = Modifier.size(20.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Presupuesto", color = Color.Gray, fontSize = 12.sp)
                        Text(text = "$${apartment.budget}", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(text = "Disponible", color = Color.Gray, fontSize = 12.sp)
                        Text(text = "$${apartment.budget - apartment.spent}", color = GreenNeon, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                BudgetProgressBar(
                    progress = (apartment.spent / apartment.budget).toFloat(),
                    color = apartment.color
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Gastado: $${apartment.spent}",
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun BudgetProgressBar(progress: Float, color: Color) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = 1000),
        label = "progress"
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color.White.copy(alpha = 0.1f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(animatedProgress)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(6.dp))
                    .background(color)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "${(progress * 100).toInt()}%",
                color = color,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
