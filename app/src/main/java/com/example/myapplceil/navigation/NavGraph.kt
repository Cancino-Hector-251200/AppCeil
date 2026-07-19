package com.example.myapplceil.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplceil.features.auth.presentation.screens.*
import com.example.myapplceil.features.dashboard.presentation.screens.*
import com.example.myapplceil.features.finances.presentation.screens.*
import com.example.myapplceil.features.profile.presentation.screens.*
import com.example.myapplceil.features.savings.presentation.screens.*
import com.example.myapplceil.features.admin.presentation.screens.*

@Composable
fun CeilNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        // 1. LOGIN
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToRegister = { navController.navigate(Screen.Register.route) },
                onLoginSuccess = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // 2. REGISTRO
        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateToLogin = { navController.popBackStack() },
                onRegisterSuccess = { isAdmin ->
                    if (isAdmin) {
                        navController.navigate(Screen.AdminMain.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    } else {
                        navController.navigate(Screen.Privacy.route)
                    }
                }
            )
        }

        // 3. PRIVACIDAD
        composable(Screen.Privacy.route) {
            PrivacyScreen(
                onAceptarTerms = {
                    navController.navigate(Screen.CategorySelection.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onRechazarTerms = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                }
            )
        }

        // 3.5. SELECCIÓN DE CATEGORÍAS
        composable(Screen.CategorySelection.route) {
            CategorySelectionScreen(
                onNavigateNext = {
                    navController.navigate(Screen.BudgetSetup.route)
                }
            )
        }

        // 4. CONFIGURACIÓN DE PRESUPUESTO
        composable(Screen.BudgetSetup.route) {
            SetupBudgetScreen(
                onSetupCompleto = { _, _ ->
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // 5. DASHBOARD
        composable(Screen.Dashboard.route) {
            DashboardScreen(navController = navController)
        }

        // 6. DEUDAS
        composable(Screen.Debts.route) {
            DebtScreen(onBack = { navController.popBackStack() })
        }

        // 7. GRÁFICAS
        composable(Screen.Graphics.route) {
            GraphicsScreen(onBack = { navController.popBackStack() })
        }

        // 8. PERFIL
        composable(Screen.Profile.route) {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() },
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0)
                    }
                }
            )
        }

        // 9. TÉRMINOS LEGALES (MENÚ)
        composable(Screen.Terms.route) {
            MenuPrivacyScreen(onNavigateBack = { navController.popBackStack() })
        }

        // 10. MEDALLAS
        composable(Screen.Medals.route) {
            MedalsScreen(onBack = { navController.popBackStack() })
        }

        // 11. APARTADOS
        composable(Screen.Apartments.route) {
            ApartmentsScreen(
                onBack = { navController.popBackStack() },
                onNavigateToTemplate = { route -> navController.navigate(route) },
                onSelectApartment = { id, type ->
                    navController.navigate(Screen.ApartmentDetail.route)
                }
            )
        }

        composable(Screen.ApartmentDetail.route) {
            ApartmentDetailScreen(onBack = { navController.popBackStack() })
        }

        // RUTAS DE PLANTILLAS
        composable(Screen.TemplateSavings.route) { 
            SavingsTemplateScreen(onBack = { navController.popBackStack() }) 
        }
        composable(Screen.TemplateEntertainment.route) { 
            EntertainmentTemplateScreen(onBack = { navController.popBackStack() }) 
        }
        composable(Screen.TemplateSchool.route) { 
            SchoolProjectTemplateScreen(onBack = { navController.popBackStack() }) 
        }
        composable(Screen.TemplateHome.route) { 
            HomeTemplateScreen(onBack = { navController.popBackStack() }) 
        }
        composable(Screen.TemplateFood.route) { 
            FoodTemplateScreen(onBack = { navController.popBackStack() }) 
        }
        composable(Screen.TemplatePersonal.route) { 
            PersonalGoalTemplateScreen(onBack = { navController.popBackStack() })
        }

        // MÓDULO ADMINISTRATIVO
        composable(Screen.AdminMain.route) {
            AdminMainScreen(onExitAdmin = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(0) { inclusive = true }
                }
            })
        }
    }
}
