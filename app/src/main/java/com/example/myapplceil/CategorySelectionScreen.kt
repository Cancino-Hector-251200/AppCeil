package com.example.myapplceil


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.ui.theme.* // Asegúrate de tener tus colores importados

// Modelo de datos para las categorías con su color respectivo
data class ExpenseCategory(val name: String, val icon: String, val color: Color)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySelectionScreen(onNavigateNext: () -> Unit = {}) {
    // Lista de categorías extraída de tu documento CEIL
    val categories = listOf(
        ExpenseCategory("Escuela", "🎓", Color(0xFF3B82F6)), // Azul
        ExpenseCategory("Entretenimiento", "🎬", Color(0xFFF59E0B)), // Naranja
        ExpenseCategory("Pasajes", "🚌", Color(0xFF10B981)), // Verde
        ExpenseCategory("Comida", "🍔", Color(0xFFEF4444)), // Rojo
        ExpenseCategory("Aseo", "🧹", Color(0xFF8B5CF6)), // Morado
        ExpenseCategory("Cuidado Personal", "🧴", Color(0xFFEC4899)), // Rosa
        ExpenseCategory("Higiene", "🧻", Color(0xFF14B8A6)), // Verde agua
        ExpenseCategory("Salidas", "🎉", Color(0xFFF43F5E)), // Rojo coral
        ExpenseCategory("Juegos", "🎮", Color(0xFF6366F1)), // Teal
        ExpenseCategory("Material Escolar", "✏️", Color(0xFF06B6D4)), // Cyan
        ExpenseCategory("Renta", "🏠", Color(0xFFEAB308)), // Amarillo
        ExpenseCategory("Luz", "💡", Color(0xFFFACC15)), // Amarillo brillante
        ExpenseCategory("Gas", "🔥", Color(0xFFF97316)), // Naranja oscuro
        ExpenseCategory("Viaje", "✈️", Color(0xFF8B5CF6)), // Indigo
        ExpenseCategory("Otros", "📦", Color(0xFF6B7280)) // Gris
    )

    // Estado para guardar las categorías que el usuario va seleccionando
    var selectedCategories by remember { mutableStateOf(setOf<String>()) }

    Scaffold(
        containerColor = NavyDark // Fondo oscuro principal
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título de la dinámica
            Text(
                text = "¡Personaliza tu perfil!",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp, top = 16.dp)
            )

            Text(
                text = "¿En qué sueles gastar tu dinero regularmente? Selecciona las categorías de tu interés.",
                fontSize = 15.sp,
                color = Color.LightGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Grid (Cuadrícula) para mostrar los botones de categorías en dos columnas
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(categories) { category ->
                    val isSelected = selectedCategories.contains(category.name)

                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            selectedCategories = if (isSelected) {
                                selectedCategories - category.name
                            } else {
                                selectedCategories + category.name
                            }
                        },
                        label = {
                            Text(
                                text = "${category.icon} ${category.name}",
                                modifier = Modifier.padding(vertical = 12.dp),
                                fontSize = 13.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = CardDark, // Fondo cuando no está seleccionado
                            labelColor = Color.LightGray,
                            selectedContainerColor = category.color, // Color dinámico cuando se selecciona
                            selectedLabelColor = Color.White
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            borderColor = if (isSelected) Color.Transparent else Color.DarkGray,
                            enabled = true,
                            selected = isSelected
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para continuar (Se activa solo si seleccionan al menos una)
            Button(
                onClick = onNavigateNext,
                enabled = selectedCategories.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MagentaNeon,
                    disabledContainerColor = Color.DarkGray
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = if (selectedCategories.isEmpty()) "Selecciona al menos una" else "Continuar",
                    color = if (selectedCategories.isEmpty()) Color.LightGray else Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}