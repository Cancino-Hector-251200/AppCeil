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
<<<<<<< HEAD
        startDestination = "login"
    ) {
=======
        startDestination = "login" // Flujo comienza en Login
    ) {
        // 1. LOGIN
>>>>>>> f0989bdba44f2db0d81d7fe104b336f4af69c600
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

<<<<<<< HEAD
        composable("register") {
            RegisterScreen(
                onNavigateToLogin = { navController.popBackStack() },
                onRegisterSuccess = { navController.navigate("privacy") }
            )
        }

        composable("privacy") {
            PrivacyScreen(
                onAceptarTerms = {
=======
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
>>>>>>> f0989bdba44f2db0d81d7fe104b336f4af69c600
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

<<<<<<< HEAD
        composable("budget_setup") {
            SetupBudgetScreen(
                onSetupCompleto = { _, _ ->
=======
        // 4. CONFIGURACIÓN DE PRESUPUESTO
        composable("budget_setup") {
            SetupBudgetScreen(
                onSetupCompleto = { monto, dias ->
                    // Configuración lista -> Al Dashboard
>>>>>>> f0989bdba44f2db0d81d7fe104b336f4af69c600
                    navController.navigate("dashboard") {
                        popUpTo("budget_setup") { inclusive = true }
                    }
                }
            )
        }

<<<<<<< HEAD
=======
        // 5. DASHBOARD (PANEL PRINCIPAL)
>>>>>>> f0989bdba44f2db0d81d7fe104b336f4af69c600
        composable("dashboard") {
            DashboardScreen(navController = navController)
        }

<<<<<<< HEAD
        composable("debts") {
            DebtScreen(onBack = { navController.popBackStack() })
        }

        composable("graphics") {
            GraphicsScreen(onBack = { navController.popBackStack() })
        }

=======
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
>>>>>>> f0989bdba44f2db0d81d7fe104b336f4af69c600
        composable("profile") {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() },
                onLogout = {
                    navController.navigate("login") {
<<<<<<< HEAD
                        popUpTo(0)
=======
                        popUpTo(0) // Limpia todo el historial
>>>>>>> f0989bdba44f2db0d81d7fe104b336f4af69c600
                    }
                }
            )
        }
<<<<<<< HEAD

        // --- MÓDULO APARTADOS ---
        composable("apartments") {
            ApartmentsScreen(
                onBack = { navController.popBackStack() },
                onCreateNew = { navController.navigate("template_personal") },
                onSelectApartment = { id -> navController.navigate("apartment_detail") },
                onNavigateToTemplate = { route -> navController.navigate(route) }
            )
        }

        composable("apartment_detail") {
            ApartmentDetailScreen(onBack = { navController.popBackStack() })
        }

        // RUTAS DE PLANTILLAS PERSONALIZADAS
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
=======
>>>>>>> f0989bdba44f2db0d81d7fe104b336f4af69c600
    }
}
