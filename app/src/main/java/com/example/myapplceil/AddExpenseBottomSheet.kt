package com.example.myapplceil


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseBottomSheet(
    onDismiss: () -> Unit,
    onSaveExpense: (titulo: String, categoria: String, monto: Double, descripcion: String) -> Unit
) {
    var titulo by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    // Control del menú desplegable de categorías
    var expandedDropdown by remember { mutableStateOf(false) }
    var categoriaSeleccionada by remember { mutableStateOf("Selecciona una opción") }

    // Lista oficial sacada de tu flujo CEIL
    val listaCategorias = listOf(
        "🍔 Comida", "🎓 Escuela", "🚌 Pasajes", "🎬 Entretenimiento",
        "🧹 Aseo", "🧴 Cuidado Personal", "🧻 Higiene", "🎉 Salidas",
        "🎮 Juegos", "✏️ Material Escolar", "🏠 Renta", "📦 Otros"
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = CardDark, // Usamos tu azul oscuro de tarjetas
        contentColor = Color.White,
        scrimColor = Color.Black.copy(alpha = 0.6f) // Oscurece el fondo del dashboard
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = " Registrar Nueva Compra",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MagentaNeon // Tu acento rosa brillante
            )

            // Campo: Título de la compra
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("¿Qué compraste?", color = Color.LightGray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MagentaNeon,
                    unfocusedBorderColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                singleLine = true
            )

            // Campo: Monto ($)
            OutlinedTextField(
                value = monto,
                onValueChange = { monto = it },
                label = { Text("Monto ($)", color = Color.LightGray) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MagentaNeon,
                    unfocusedBorderColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                singleLine = true
            )

            // Selector de Tipo de Compra (Dropdown Menu)
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Tipo de compra:", color = Color.LightGray, fontSize = 12.sp, modifier = Modifier.padding(start = 4.dp, bottom = 4.dp))
                Box {
                    OutlinedTextField(
                        value = categoriaSeleccionada,
                        onValueChange = {},
                        readOnly = true, // Evita que escriban texto libre
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.clickable { expandedDropdown = true }
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MagentaNeon,
                            unfocusedBorderColor = Color.Gray,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )
                    // Capa invisible encima para capturar el clic en todo el campo
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable { expandedDropdown = true }
                    )

                    DropdownMenu(
                        expanded = expandedDropdown,
                        onDismissRequest = { expandedDropdown = false },
                        modifier = Modifier.fillMaxWidth(0.85f)
                    ) {
                        listaCategorias.forEach { cat ->
                            DropdownMenuItem(
                                text = { Text(cat) },
                                onClick = {
                                    categoriaSeleccionada = cat
                                    expandedDropdown = false
                                }
                            )
                        }
                    }
                }
            }

            // Campo: Descripción Opcional
            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción (Opcional)", color = Color.LightGray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                maxLines = 3,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MagentaNeon,
                    unfocusedBorderColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Botón: Guardar Movimiento
            val esValido = titulo.isNotBlank() && monto.toDoubleOrNull() != null && categoriaSeleccionada != "Selecciona una opción"

            Button(
                onClick = {
                    val valorMonto = monto.toDoubleOrNull() ?: 0.0
                    onSaveExpense(titulo, categoriaSeleccionada, valorMonto, descripcion)
                },
                enabled = esValido,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MagentaNeon,
                    disabledContainerColor = Color.DarkGray
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Guardar Movimiento", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}
