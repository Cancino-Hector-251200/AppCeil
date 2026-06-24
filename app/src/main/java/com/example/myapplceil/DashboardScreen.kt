package com.example.myapplceil

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.myapplceil.ui.theme.*
import kotlinx.coroutines.launch

data class Transaction(
    val id: Int,
    val name: String,
    val amount: String,
    val categoryColor: Color
)

@Composable
fun DashboardScreen() {
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
                onCloseDrawer = { scope.launch { drawerState.close() } }
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
                // --- Tarjeta Superior de Presupuesto ---
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
                        // Fila Superior: Título e Iconos
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Monto total",
                                color = Color.LightGray,
                                fontSize = 16.sp
                            )
                            Row {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = "Fecha",
                                    tint = Color.LightGray,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                // Icono de Menú de Hamburguesa para abrir el drawer
                                IconButton(
                                    onClick = { scope.launch { drawerState.open() } },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Menu Lateral",
                                        tint = Color.LightGray
                                    )
                                }
                            }
                        }

                        // Monto Central
                        Text(
                            text = "$1500",
                            color = Color.White,
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        // Fila Inferior: Editar y Botones +/-
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Editar",
                                color = MagentaNeon,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Sumar",
                                tint = MagentaNeon,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = "Restar",
                                tint = MagentaNeon,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // --- Encabezado de la Lista (Historial) ---
                Text(
                    text = "Historial",
                    color = MagentaNeon,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Cabecera de la Tabla
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Nombre de compra", color = Color.Gray, fontSize = 12.sp)
                    Text(text = "$ Monto", color = Color.Gray, fontSize = 12.sp)
                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Gray.copy(alpha = 0.2f)
                )

                // --- Lista de Gastos ---
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
fun CeilDrawerContent(onCloseDrawer: () -> Unit) {
    ModalDrawerSheet(
        drawerContainerColor = NavyDark,
        drawerContentColor = Color.White,
        drawerShape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
        modifier = Modifier
            .fillMaxHeight()
            .width(300.dp)
    ) {
        // --- Header del Menú ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onCloseDrawer) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Atrás",
                    tint = Color.LightGray
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Menu",
                color = MagentaNeon,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        HorizontalDivider(color = Color.Gray.copy(alpha = 0.3f))

        Spacer(modifier = Modifier.height(16.dp))

        // --- Lista de Opciones ---
        CeilDrawerItem(icon = Icons.Default.Person, label = "Perfil")
        CeilDrawerItem(icon = Icons.Default.EmojiEvents, label = "Medallas/Logros")
        CeilDrawerItem(icon = Icons.Default.SwapHoriz, label = "Me deben y debo")
        CeilDrawerItem(icon = Icons.Default.PieChart, label = "Gráficas")
        CeilDrawerItem(icon = Icons.Default.Folder, label = "Apartados")
        CeilDrawerItem(icon = Icons.Default.Help, label = "Ayuda")
    }
}

@Composable
fun CeilDrawerItem(icon: ImageVector, label: String) {
    NavigationDrawerItem(
        icon = { Icon(imageVector = icon, contentDescription = null, tint = MagentaNeon) },
        label = { Text(text = label, color = Color.White, fontWeight = FontWeight.Medium) },
        selected = false,
        onClick = { /* Handle navigation here */ },
        colors = NavigationDrawerItemDefaults.colors(
            unselectedContainerColor = Color.Transparent,
            selectedContainerColor = MagentaNeon.copy(alpha = 0.1f)
        ),
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
    )
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Indicador de Categoría
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(transaction.categoryColor)
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Nombre
        Text(
            text = transaction.name,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        // Monto
        Text(
            text = transaction.amount,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Acciones
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Editar",
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Eliminar",
            tint = Color.Red.copy(alpha = 0.7f),
            modifier = Modifier.size(20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    MyApplCeilTheme {
        DashboardScreen()
    }
}
