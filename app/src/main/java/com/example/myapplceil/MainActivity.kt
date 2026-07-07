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
        // 1. LOGIN
        composable("login") {
            LoginScreen(
                onNavigateToRegister = { navController.navigate("register") },
                onLoginSuccess = {
                    // Por defecto, el login lleva al Dashboard de Usuario
                    navController.navigate("dashboard") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // 2. REGISTRO (Aquí es donde se selecciona el Rol)
        composable("register") {
            RegisterScreen(
                onNavigateToLogin = { navController.popBackStack() },
                onRegisterSuccess = { isAdmin ->
                    if (isAdmin) {
                        // Si se registra como Admin, va al panel administrativo
                        navController.navigate("admin_main") {
                            popUpTo("login") { inclusive = true }
                        }
                    } else {
                        // Los usuarios normales pasan por el flujo de privacidad
                        navController.navigate("privacy")
                    }
                }
            )
        }

        // 3. PRIVACIDAD
        composable("privacy") {
            PrivacyScreen(
                onAceptarTerms = {
                    navController.navigate("category_selection") {
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

        // 3.5. SELECCIÓN DE CATEGORÍAS
        composable("category_selection") {
            CategorySelectionScreen(
                onNavigateNext = {
                    navController.navigate("budget_setup")
                }
            )
        }

        // 4. CONFIGURACIÓN DE PRESUPUESTO
        composable("budget_setup") {
            SetupBudgetScreen(
                onSetupCompleto = { _, _ ->
                    // Navegamos al dashboard y limpiamos todo el flujo de registro
                    navController.navigate("dashboard") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // 5. DASHBOARD
        composable("dashboard") {
            DashboardScreen(navController = navController)
        }

        // 6. DEUDAS
        composable("debts") {
            DebtScreen(onBack = { navController.popBackStack() })
        }

        // 7. GRÁFICAS
        composable("graphics") {
            GraphicsScreen(onBack = { navController.popBackStack() })
        }

        // 8. PERFIL
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

        // 9. TÉRMINOS LEGALES (MENÚ)
        composable("terms") {
            MenuPrivacyScreen(onNavigateBack = { navController.popBackStack() })
        }

        // 10. MEDALLAS
        composable("medals") {
            MedalsScreen(onBack = { navController.popBackStack() })
        }

        // 11. APARTADOS
        composable("apartments") {
            ApartmentsScreen(
                onBack = { navController.popBackStack() },
                onNavigateToTemplate = { route -> navController.navigate(route) },
                onSelectApartment = { id, type ->
                    navController.navigate("apartment_detail")
                }
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

        // MÓDULO ADMINISTRATIVO
        composable("admin_main") {
            AdminMainScreen(onExitAdmin = {
                // Al salir del panel admin, regresamos al login para seguridad
                navController.navigate("login") {
                    popUpTo(0) { inclusive = true }
                }
            })
        }
    }
}
