package com.example.myapplceil

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
// IMPORTACIÓN GLOBAL DEL TEMA
import com.example.myapplceil.ui.theme.*

data class Transaction(
    val id: Int,
    val name: String,
    val amount: String,
    val categoryColor: Color
)

@Composable
fun DashboardScreen(navController: NavController = rememberNavController()) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val transactions = listOf(
        Transaction(1, "Comidas", "$50", Color.Red),
        Transaction(2, "Transporte", "$20", Color.Cyan),
        Transaction(3, "Suscripción", "$15", Color.Green),
        Transaction(4, "Entretenimiento", "$40", Color.Yellow)
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            CeilDrawerContent(
                onCloseDrawer = { scope.launch { drawerState.close() } },
                onNavigateToMedals = {
                    scope.launch {
                        drawerState.close()
                        navController.navigate("medals")
                    }
                },
                onNavigateToDebts = {
                    scope.launch {
                        drawerState.close()
                        navController.navigate("debts")
                    }
                },
                onNavigateToGraphics = {
                    scope.launch {
                        drawerState.close()
                        navController.navigate("graphics")
                    }
                },
                onNavigateToProfile = {
                    scope.launch {
                        drawerState.close()
                        navController.navigate("profile")
                    }
                },
                onNavigateToApartments = {
                    scope.launch {
                        drawerState.close()
                        navController.navigate("apartments")
                    }
                }
            )
        }
    ) {
        Scaffold(
            containerColor = NavyDark,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /* Add Action */ },
                    containerColor = MagentaNeon,
                    contentColor = Color.White,
                    shape = CircleShape
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Añadir")
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                MoneyCard(onMenuOpen = { scope.launch { drawerState.open() } })

                Spacer(modifier = Modifier.height(32.dp))
                Text(text = "Historial", color = MagentaNeon, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(transactions) { transaction ->
                        TransactionItem(transaction)
                    }
                }
            }
        }
    }
}

@Composable
fun MoneyCard(onMenuOpen: () -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "💰 Mi dinero", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                Row {
                    IconButton(onClick = { isExpanded = !isExpanded }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar ingreso",
                            tint = if (isExpanded) MagentaNeon else Color.LightGray
                        )
                    }
                    IconButton(onClick = onMenuOpen) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.LightGray)
                    }
                }
            }

            AnimatedVisibility(visible = !isExpanded) {
                Column {
                    Text(
                        text = "$1500",
                        color = Color.White,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    FrequencyChip("Quincenal")
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = "Disponible actualmente", color = Color.LightGray, fontSize = 12.sp)
                            Text(text = "$920", color = GreenNeon, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(text = "Presupuesto diario", color = Color.LightGray, fontSize = 12.sp)
                            Text(text = "$85", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    SavingsProgress(progress = 0.4f)
                }
            }

            AnimatedVisibility(visible = isExpanded) {
                ExpandedMoneyCard(onSave = { isExpanded = false })
            }
        }
    }
}

@Composable
fun FrequencyChip(label: String) {
    Surface(
        color = Color(0xFF2D1B4D),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, MagentaNeon.copy(alpha = 0.3f))
    ) {
        Text(
            text = "🗓 $label",
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun SavingsProgress(progress: Float) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Ahorro", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Text(text = "${(progress * 100).toInt()}%", color = YellowNeon, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(5.dp)),
            color = YellowNeon,
            trackColor = Color.White.copy(alpha = 0.1f),
        )
    }
}

@Composable
fun ExpandedMoneyCard(onSave: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Editar ingreso", color = MagentaNeon, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = "$1500",
            onValueChange = {},
            label = { Text("Monto", color = Color.LightGray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = MagentaNeon,
                unfocusedBorderColor = Color.Gray
            )
        )
        OutlinedTextField(
            value = "Quincenal",
            onValueChange = {},
            label = { Text("Frecuencia", color = Color.LightGray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.White) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = MagentaNeon,
                unfocusedBorderColor = Color.Gray
            )
        )
        OutlinedTextField(
            value = "$500",
            onValueChange = {},
            label = { Text("Meta de ahorro", color = Color.LightGray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = MagentaNeon,
                unfocusedBorderColor = Color.Gray
            )
        )
        Button(
            onClick = onSave,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Guardar", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun CeilDrawerContent(
    onCloseDrawer: () -> Unit,
    onNavigateToMedals: () -> Unit,
    onNavigateToDebts: () -> Unit,
    onNavigateToGraphics: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToApartments: () -> Unit
) {
    ModalDrawerSheet(
        drawerContainerColor = NavyDark,
        drawerContentColor = Color.White,
        modifier = Modifier.width(300.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onCloseDrawer) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.LightGray)
            }
            Text(text = "Menu", color = MagentaNeon, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        HorizontalDivider(color = Color.Gray.copy(alpha = 0.3f))
        Spacer(modifier = Modifier.height(16.dp))

        CeilDrawerItem(icon = Icons.Default.Person, label = "Perfil", onClick = onNavigateToProfile)
        CeilDrawerItem(icon = Icons.Default.EmojiEvents, label = "Medallas", onClick = onNavigateToMedals)
        CeilDrawerItem(icon = Icons.Default.SwapHoriz, label = "Me deben y debo", onClick = onNavigateToDebts)
        CeilDrawerItem(icon = Icons.Default.PieChart, label = "Gráficas", onClick = onNavigateToGraphics)
        CeilDrawerItem(icon = Icons.Default.Folder, label = "Apartados", onClick = onNavigateToApartments)
    }
}

@Composable
fun CeilDrawerItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    NavigationDrawerItem(
        icon = { Icon(icon, contentDescription = null, tint = MagentaNeon) },
        label = { Text(label, color = Color.White) },
        selected = false,
        onClick = onClick,
        colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent),
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
    )
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(12.dp).clip(CircleShape).background(transaction.categoryColor))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = transaction.name, color = Color.White, fontSize = 16.sp, modifier = Modifier.weight(1f))
        Text(text = transaction.amount, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    DashboardScreen()
}
