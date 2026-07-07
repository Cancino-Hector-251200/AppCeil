package com.example.myapplceil

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.ui.theme.*

data class Debt(
    val id: Int,
    val name: String,
    val amount: Double,
    val isMeDeben: Boolean
)

@Composable
fun DebtScreen(onBack: () -> Unit = {}) {
    var selectedTab by remember { mutableIntStateOf(0) }
    var showAddSheet by remember { mutableStateOf(false) }
    
    // Lista dinámica de deudas
    val debtsList = remember {
        mutableStateListOf(
            Debt(1, "Carlos", 120.0, true),
            Debt(2, "Sofia", 250.0, true),
            Debt(3, "Ximena", 230.0, true),
            Debt(4, "Andres", 80.0, false),
            Debt(5, "Tienda", 70.0, false)
        )
    }

    // Cálculos de balance
    val totalMeDeben = debtsList.filter { it.isMeDeben }.sumOf { it.amount }
    val totalDebo = debtsList.filter { !it.isMeDeben }.sumOf { it.amount }

    Scaffold(
        containerColor = NavyDark,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddSheet = true },
                containerColor = MagentaNeon,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier.shadow(15.dp, CircleShape, spotColor = MagentaNeon)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar", modifier = Modifier.size(30.dp))
            }
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
                .padding(horizontal = 20.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
                    }
                    Text("Me deben y Debo", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
                Icon(Icons.Default.FilterList, contentDescription = null, tint = Color.White)
            }

            // Tarjeta Balance
            Card(
                colors = CardDefaults.cardColors(containerColor = CardDark),
                shape = RoundedCornerShape(28.dp),
                modifier = Modifier.fillMaxWidth().shadow(10.dp, RoundedCornerShape(28.dp), spotColor = PurpleNeon)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Balance total pendiente", color = Color.White.copy(0.6f), fontSize = 14.sp)
                    Spacer(Modifier.height(12.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text("Me deben", color = GreenNeon, fontSize = 13.sp)
                            Text("$${totalMeDeben.toInt()}", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Black)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("Debo", color = RedNeon, fontSize = 13.sp)
                            Text("$${totalDebo.toInt()}", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Black)
                        }
                    }
                    HorizontalDivider(Modifier.padding(vertical = 16.dp), color = Color.White.copy(0.1f))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Resultado neto:", color = Color.White, fontWeight = FontWeight.Medium)
                        val neto = totalMeDeben - totalDebo
                        Text(
                            text = (if (neto >= 0) "+" else "") + "$${neto.toInt()}", 
                            color = if (neto >= 0) GreenNeon else RedNeon, 
                            fontSize = 18.sp, 
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // Selector de Tabs
            Row(
                modifier = Modifier.fillMaxWidth().height(56.dp).clip(RoundedCornerShape(18.dp)).background(CardDark).padding(5.dp)
            ) {
                val colorLeft by animateColorAsState(if (selectedTab == 0) MagentaNeon else Color.Transparent, label = "")
                val colorRight by animateColorAsState(if (selectedTab == 1) MagentaNeon else Color.Transparent, label = "")

                Box(Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(14.dp)).background(colorLeft).clickable { selectedTab = 0 }, contentAlignment = Alignment.Center) {
                    Text("🟢 Me deben", color = Color.White, fontWeight = if(selectedTab == 0) FontWeight.Bold else FontWeight.Normal)
                }
                Box(Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(14.dp)).background(colorRight).clickable { selectedTab = 1 }, contentAlignment = Alignment.Center) {
                    Text("🔴 Debo", color = Color.White, fontWeight = if(selectedTab == 1) FontWeight.Bold else FontWeight.Normal)
                }
            }

            Spacer(Modifier.height(20.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp), contentPadding = PaddingValues(bottom = 100.dp)) {
                val filteredList = debtsList.filter { (selectedTab == 0 && it.isMeDeben) || (selectedTab == 1 && !it.isMeDeben) }
                items(filteredList) { debt ->
                    DebtItemCard(
                        debt = debt, 
                        onDelete = { debtsList.remove(debt) }
                    )
                }
            }
        }
    }

    if (showAddSheet) {
        AddDebtBottomSheet(
            isMeDeben = selectedTab == 0,
            onDismiss = { showAddSheet = false },
            onSave = { name, amount ->
                val doubleAmount = amount.toDoubleOrNull() ?: 0.0
                if (name.isNotBlank() && doubleAmount > 0) {
                    debtsList.add(Debt(debtsList.size + 1, name, doubleAmount, selectedTab == 0))
                    showAddSheet = false
                }
            }
        )
    }
}

@Composable
fun DebtItemCard(debt: Debt, onDelete: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardDark),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(46.dp).clip(CircleShape).background(PurpleNeon.copy(0.15f)), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Person, null, tint = PurpleNeon)
                }
                Spacer(Modifier.width(14.dp))
                Column(Modifier.weight(1f)) {
                    Text(debt.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                    Text("Pendiente", color = YellowNeon, fontSize = 12.sp)
                }
                Text(
                    text = (if (debt.isMeDeben) "+" else "-") + "$${debt.amount.toInt()}",
                    color = if (debt.isMeDeben) GreenNeon else RedNeon,
                    fontWeight = FontWeight.Black,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { }, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Default.Edit, "Editar", tint = Color.White.copy(0.6f), modifier = Modifier.size(20.dp))
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = onDelete, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Default.Delete, "Eliminar", tint = RedNeon.copy(0.8f), modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDebtBottomSheet(isMeDeben: Boolean, onDismiss: () -> Unit, onSave: (String, String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    ModalBottomSheet(onDismissRequest = onDismiss, containerColor = CardDark) {
        Column(modifier = Modifier.padding(24.dp).padding(bottom = 32.dp)) {
            Text(
                text = if (isMeDeben) "Nueva persona que me debe" else "Nueva deuda mía", 
                color = Color.White, 
                fontSize = 20.sp, 
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = name, 
                onValueChange = { name = it }, 
                label = { Text("Nombre de la persona") }, 
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MagentaNeon,
                    unfocusedBorderColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = amount, 
                onValueChange = { amount = it }, 
                label = { Text("Monto ($)") }, 
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MagentaNeon,
                    unfocusedBorderColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = { onSave(name, amount) }, 
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Guardar Registro", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DebtPreview() {
    DebtScreen()
}
