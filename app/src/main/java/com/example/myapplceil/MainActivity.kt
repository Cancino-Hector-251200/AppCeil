package com.example.myapplceil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplceil.ui.theme.MyApplCeilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplCeilTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CeilNavigation()
                }
            }
        }
    }
}

@Composable
fun CeilNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onNavigateToRegister = { navController.navigate("register") },
                onLoginSuccess = {
                    navController.navigate("dashboard") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("register") {
            RegisterScreen(
                onNavigateToLogin = { navController.popBackStack() },
                onRegisterSuccess = { navController.navigate("privacy") }
            )
        }

        composable("privacy") {
            PrivacyScreen(
                onAceptarTerms = {
                    navController.navigate("budget_setup") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onRechazarTerms = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

        composable("budget_setup") {
            SetupBudgetScreen(
                onSetupCompleto = { _, _ ->
                    navController.navigate("dashboard") {
                        popUpTo("budget_setup") { inclusive = true }
                    }
                }
            )
        }

        composable("dashboard") {
            DashboardScreen(navController = navController)
        }

        composable("debts") {
            DebtScreen(onBack = { navController.popBackStack() })
        }

        composable("graphics") {
            GraphicsScreen(onBack = { navController.popBackStack() })
        }

        composable("profile") {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() },
                onLogout = {
                    navController.navigate("login") {
                        popUpTo(0)
                    }
                }
            )
        }

        // --- MÓDULO APARTADOS ---
        composable("apartments") {
            ApartmentsScreen(
                onBack = { navController.popBackStack() },
                onSelectApartment = { _ -> navController.navigate("apartment_detail") },
                onNavigateToTemplate = { route -> navController.navigate(route) }
            )
        }

        composable("apartment_detail") {
            ApartmentDetailScreen(onBack = { navController.popBackStack() })
        }

        // RUTAS DE PLANTILLAS
        composable("template_savings") { 
            SavingsTemplateScreen(onBack = { navController.popBackStack() }) 
        }
        composable("template_entertainment") { 
            EntertainmentTemplateScreen(onBack = { navController.popBackStack() }) 
        }
        composable("template_school") { 
            SchoolProjectTemplateScreen(onBack = { navController.popBackStack() }) 
        }
        composable("template_home") { 
            HomeTemplateScreen(onBack = { navController.popBackStack() }) 
        }
        composable("template_food") { 
            FoodTemplateScreen(onBack = { navController.popBackStack() }) 
        }
        composable("template_personal") { 
            PersonalGoalTemplateScreen(onBack = { navController.popBackStack() }) 
        }
    }
}
