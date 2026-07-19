package com.example.myapplceil.ui.features.admin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myapplceil.core.theme.*

sealed class AdminScreen(val icon: ImageVector, val label: String) {
    object Dashboard : AdminScreen(Icons.Default.Dashboard, "Inicio")
    object Users : AdminScreen(Icons.Default.People, "Usuarios")
    object Stats : AdminScreen(Icons.Default.BarChart, "Stats")
    object Quizzes : AdminScreen(Icons.Default.Quiz, "Edu")
}

@Composable
fun AdminMainScreen(onExitAdmin: () -> Unit) {
    var currentScreen by remember { mutableStateOf<AdminScreen>(AdminScreen.Dashboard) }

    Scaffold(
        containerColor = NavyDark,
        bottomBar = {
            NavigationBar(
                containerColor = CardDark,
                contentColor = Color.White,
            ) {
                val items = listOf(
                    AdminScreen.Dashboard,
                    AdminScreen.Users,
                    AdminScreen.Stats,
                    AdminScreen.Quizzes
                )
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentScreen == screen,
                        onClick = { currentScreen = screen },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MagentaNeon,
                            selectedTextColor = MagentaNeon,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = MagentaNeon.copy(alpha = 0.1f)
                        )
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onExitAdmin,
                containerColor = Color.Red.copy(alpha = 0.8f),
                contentColor = Color.White
            ) {
                Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Salir Admin")
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (currentScreen) {
                AdminScreen.Dashboard -> AdminDashboardScreen()
                AdminScreen.Users -> AdminUsersScreen()
                AdminScreen.Stats -> AdminStatisticsScreen()
                AdminScreen.Quizzes -> AdminQuizAnalyticsScreen()
            }
        }
    }
}
