package com.example.myapplceil

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    val scrollState = rememberScrollState()
    val NavyCard = Color(0xFF151E3D)
    val SuccessGreen = Color(0xFF4CAF50)
    val ErrorRed = Color(0xFFF44336)

    Scaffold(
        bottomBar = { CeilBottomNavigation() },
        containerColor = NavyDark
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // --- Header Section ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Hola, Juan",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Nivel 5 - Ahorrador",
                        color = Color.LightGray,
                        fontSize = 12.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .border(2.dp, MagentaNeon, CircleShape)
                        .padding(3.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Profile",
                        tint = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- Primary Card: Daily Spending Limit ---
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = NavyCard)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Disponible para hoy",
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "$150.00",
                        color = Color.White,
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Black
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    LinearProgressIndicator(
                        progress = { 0.7f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        color = MagentaNeon,
                        trackColor = NavyDark
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Quick Actions Row ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ActionButton(
                    text = "Gasto",
                    icon = Icons.Outlined.ArrowDownward,
                    containerColor = ErrorRed.copy(alpha = 0.2f),
                    contentColor = ErrorRed,
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    text = "Ingreso",
                    icon = Icons.Outlined.ArrowUpward,
                    containerColor = SuccessGreen.copy(alpha = 0.2f),
                    contentColor = SuccessGreen,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Debo y Me Deben Section ---
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = NavyCard)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Debo", color = Color.White, fontSize = 12.sp)
                        Text(text = "$50", color = ErrorRed, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                    Divider(
                        modifier = Modifier
                            .height(40.dp)
                            .width(1.dp),
                        color = Color.Gray.copy(alpha = 0.3f)
                    )
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Me deben", color = Color.White, fontSize = 12.sp)
                        Text(text = "$120", color = SuccessGreen, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Gamification: Reto Activo ---
            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, MagentaNeon),
                colors = CardDefaults.outlinedCardColors(containerColor = Color.Transparent)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.EmojiEvents,
                        contentDescription = "Challenge",
                        tint = MagentaNeon,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Reto: 3 días sin gasto hormiga",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        LinearProgressIndicator(
                            progress = { 0.66f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp)),
                            color = MagentaNeon,
                            trackColor = Color.Gray.copy(alpha = 0.3f)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "2/3", color = MagentaNeon, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ActionButton(
    text: String,
    icon: ImageVector,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = { /* Action */ },
        modifier = modifier.height(60.dp),
        shape = RoundedCornerShape(16.dp),
        color = containerColor
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = icon, contentDescription = text, tint = contentColor)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, color = contentColor, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun CeilBottomNavigation() {
    NavigationBar(
        containerColor = Color(0xFF0B132B), // NavyDark
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Home, contentDescription = "Home") },
            label = { Text("Inicio") },
            selected = true,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MagentaNeon,
                selectedTextColor = MagentaNeon,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            ),
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Handshake, contentDescription = "Debts") },
            label = { Text("Deudas") },
            selected = false,
            onClick = { }
        )
        // Middle Add Button
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            FloatingActionButton(
                onClick = { },
                containerColor = MagentaNeon,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(Icons.Outlined.Add, contentDescription = "Add")
            }
        }
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.EmojiEvents, contentDescription = "Rewards") },
            label = { Text("Retos") },
            selected = false,
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Person, contentDescription = "Profile") },
            label = { Text("Perfil") },
            selected = false,
            onClick = { }
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
