package com.example.myapplceil

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.ui.theme.*

@Composable
fun ApartmentDetailScreen(
    apartmentName: String = "Proyecto escolar",
    type: String = "Escolar",
    onBack: () -> Unit = {}
) {
    var showAddExpense by remember { mutableStateOf(false) }
    
    // Mock data
    val budget = 1200.0
    val spent = 350.0
    val available = budget - spent
    
    val expenses = remember {
        mutableStateListOf<ApartmentExpense>(
            ApartmentExpense(1, "🖨", "Impresiones", 50.0),
            ApartmentExpense(2, "📚", "Materiales", 200.0),
            ApartmentExpense(3, "🚍", "Transporte", 100.0)
        )
    }

    Scaffold(
        containerColor = NavyDark,
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = Color.White)
                }
                Text(text = apartmentName, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }
        },
        floatingActionButton = {
            if (!showAddExpense) {
                ExtendedFloatingActionButton(
                    onClick = { showAddExpense = true },
                    containerColor = MagentaNeon,
                    contentColor = Color.White,
                    shape = RoundedCornerShape(16.dp),
                    icon = { Icon(Icons.Default.Add, null) },
                    text = { Text("Agregar gasto") }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Column(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
                ApartmentSummaryCard(
                    title = apartmentName,
                    budget = budget,
                    spent = spent,
                    available = available
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text("Movimientos", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 100.dp)
                ) {
                    items(expenses) { expense ->
                        ExpenseItem(expense)
                    }
                }
            }

            // Overlay for adding expense
            AnimatedVisibility(
                visible = showAddExpense,
                enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                AddExpenseCard(
                    onClose = { showAddExpense = false },
                    onSave = { emoji, name, amount ->
                        expenses.add(0, ApartmentExpense(expenses.size + 1, emoji, name, amount))
                        showAddExpense = false
                    }
                )
            }
        }
    }
}

@Composable
fun AddExpenseCard(onClose: () -> Unit, onSave: (String, String, Double) -> Unit) {
    var expenseName by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var selectedEmoji by remember { mutableStateOf("🍔") }

    Card(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.85f),
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("Nuevo gasto", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                IconButton(onClick = onClose) { Icon(Icons.Default.Close, null, tint = Color.Gray) }
            }
            
            Spacer(modifier = Modifier.height(20.dp))
            
            Text("Selecciona un icono", color = Color.Gray, fontSize = 14.sp)
            EmojiSelector(selectedEmoji) { selectedEmoji = it }
            
            Spacer(modifier = Modifier.height(20.dp))
            
            CeilTextField(label = "Nombre del gasto", value = expenseName, onValueChange = { expenseName = it }, placeholder = "Ej. Hamburguesa")
            Spacer(modifier = Modifier.height(16.dp))
            CeilTextField(label = "Monto", value = amount, onValueChange = { amount = it }, placeholder = "0.00", prefix = { Text("$ ", color = Color.White) })
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text("Vista previa", color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            ExpenseCard(emoji = selectedEmoji, name = expenseName.ifEmpty { "Gasto" }, amount = amount.toDoubleOrNull() ?: 0.0)
            
            Spacer(modifier = Modifier.weight(1f))
            
            Button(
                onClick = { onSave(selectedEmoji, expenseName, amount.toDoubleOrNull() ?: 0.0) },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Guardar gasto", fontWeight = FontWeight.Bold)
            }
        }
    }
}
