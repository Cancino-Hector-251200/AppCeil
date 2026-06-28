package com.example.myapplceil

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CreateApartmentScreen(onBack: () -> Unit = {}) {
    var name by remember { mutableStateOf("") }
    var budget by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val categories = listOf("Escolar", "Comida", "Casa", "Ahorro", "Personal", "Otro")
    var selectedCategory by remember { mutableStateOf(categories[0]) }

    Scaffold(
        containerColor = NavyDark,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = Color.White)
                }
                Text(
                    text = "Nuevo apartado",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            CreateApartmentForm(
                name = name,
                onNameChange = { name = it },
                selectedCategory = selectedCategory,
                categories = categories,
                onCategorySelect = { selectedCategory = it },
                budget = budget,
                onBudgetChange = { budget = it },
                deadline = deadline,
                onDeadlineChange = { deadline = it },
                isExpanded = expanded,
                onExpandedChange = { expanded = it }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { /* Logic to save */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Crear apartado",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CreateApartmentForm(
    name: String,
    onNameChange: (String) -> Unit,
    selectedCategory: String,
    categories: List<String>,
    onCategorySelect: (String) -> Unit,
    budget: String,
    onBudgetChange: (String) -> Unit,
    deadline: String,
    onDeadlineChange: (String) -> Unit,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        CeilTextField(
            label = "Nombre del apartado",
            value = name,
            onValueChange = onNameChange,
            placeholder = "Ej. Proyecto escolar"
        )

        Column {
            Text(text = "Tipo", color = Color.LightGray, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
            Box {
                OutlinedCard(
                    onClick = { onExpandedChange(true) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.outlinedCardColors(containerColor = CardDark),
                    border = CardDefaults.outlinedCardBorder(enabled = true).copy(brush = androidx.compose.ui.graphics.SolidColor(Color.Gray))
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = selectedCategory, color = Color.White)
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = MagentaNeon)
                    }
                }
                DropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { onExpandedChange(false) },
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .background(CardDark)
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category, color = Color.White) },
                            onClick = {
                                onCategorySelect(category)
                                onExpandedChange(false)
                            }
                        )
                    }
                }
            }
        }

        CeilTextField(
            label = "Presupuesto",
            value = budget,
            onValueChange = onBudgetChange,
            placeholder = "$ 0.00",
            prefix = { Text("$ ", color = Color.White) }
        )

        CeilTextField(
            label = "Fecha límite",
            value = deadline,
            onValueChange = onDeadlineChange,
            placeholder = "DD/MM/AAAA",
            trailingIcon = { Icon(Icons.Default.CalendarToday, contentDescription = null, tint = MagentaNeon) }
        )
    }
}
