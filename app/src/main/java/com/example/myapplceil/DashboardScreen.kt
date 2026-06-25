package com.example.myapplceil

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.myapplceil.ui.theme.*
import kotlinx.coroutines.launch

// Colores del tema (en caso de que no carguen del theme)
val NavyDark = Color(0xFF0B1120)
val MagentaNeon = Color(0xFFF000D8)

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
                onNavigateToDebts = {
                    scope.launch {
                        drawerState.close()
                        navController.navigate("debts")
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
                // Tarjeta de Presupuesto
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF151E3D))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Monto total", color = Color.LightGray, fontSize = 16.sp)
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.LightGray)
                            }
                        }
                        Text(
                            text = "$1500",
                            color = Color.White,
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            Text(text = "Editar", color = MagentaNeon, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

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
fun CeilDrawerContent(onCloseDrawer: () -> Unit, onNavigateToDebts: () -> Unit) {
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

        CeilDrawerItem(icon = Icons.Default.Person, label = "Perfil", onClick = {})
        CeilDrawerItem(icon = Icons.Default.EmojiEvents, label = "Medallas", onClick = {})
        CeilDrawerItem(icon = Icons.Default.SwapHoriz, label = "Me deben y debo", onClick = onNavigateToDebts)
        CeilDrawerItem(icon = Icons.Default.PieChart, label = "Gráficas", onClick = {})
        CeilDrawerItem(icon = Icons.Default.Folder, label = "Apartados", onClick = {})
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
