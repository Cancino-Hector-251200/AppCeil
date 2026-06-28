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
        startDestination = "login" // Flujo comienza en Login
    ) {
        // 1. LOGIN
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

        // 2. REGISTRO
        composable("register") {
            RegisterScreen(
                onNavigateToLogin = { navController.popBackStack() },
                onRegisterSuccess = { 
                    // Después de registrarse, va a Privacidad
                    navController.navigate("privacy") 
                }
            )
        }

        // 3. PRIVACIDAD
        composable("privacy") {
            PrivacyScreen(
                onAceptarTerms = {
                    // Al aceptar, configuramos el presupuesto
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

        // 4. CONFIGURACIÓN DE PRESUPUESTO
        composable("budget_setup") {
            SetupBudgetScreen(
                onSetupCompleto = { monto, dias ->
                    // Configuración lista -> Al Dashboard
                    navController.navigate("dashboard") {
                        popUpTo("budget_setup") { inclusive = true }
                    }
                }
            )
        }

        // 5. DASHBOARD (PANEL PRINCIPAL)
        composable("dashboard") {
            DashboardScreen(navController = navController)
        }

        // 6. DEUDAS
        composable("debts") {
            DebtScreen(onBack = { 
                navController.popBackStack()
            })
        }

        // 7. GRÁFICAS
        composable("graphics") {
            GraphicsScreen(onBack = {
                navController.popBackStack()
            })
        }

        // 8. PERFIL
        composable("profile") {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() },
                onLogout = {
                    navController.navigate("login") {
                        popUpTo(0) // Limpia todo el historial
                    }
                }
            )
        }

        // 9. APARTADOS (Nuevas pantallas)
        composable("apartments") {
            ApartmentsScreen(
                onBack = { navController.popBackStack() },
                onCreateNew = { navController.navigate("create_apartment") },
                onSelectApartment = { id -> navController.navigate("apartment_detail") }
            )
        }

        composable("create_apartment") {
            CreateApartmentScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable("apartment_detail") {
            ApartmentDetailScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
