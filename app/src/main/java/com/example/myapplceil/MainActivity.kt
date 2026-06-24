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

// Importante: Asegúrate de agregar esta dependencia en build.gradle.kts (app):
// implementation("androidx.navigation:navigation-compose:2.8.5")

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
        // Ruta Inicial: Login
        composable("login") {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate("register")
                },
                onNavigateToDashboard = {
                    navController.navigate("dashboard") {
                        // Al iniciar sesión, limpiamos el historial para que no pueda volver atrás al login
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // Ruta de Registro
        composable("register") {
            RegisterScreen(
                onNavigateToLogin = {
                    // Regresar al login (pop de la pila)
                    navController.popBackStack()
                },
                onNavigateToDashboard = {
                    // Al completar el registro, navegamos al aviso de privacidad
                    navController.navigate("privacy")
                }
            )
        }

        // Ruta de Privacidad
        composable("privacy") {
            PrivacyScreen(
                onAceptarTerms = {
                    // Al aceptar, vamos al Dashboard y eliminamos TODO el historial previo
                    navController.navigate("dashboard") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onRechazarTerms = {
                    // Al rechazar, regresamos al Login
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

        // Ruta Principal: Dashboard
        composable("dashboard") {
            DashboardScreen()
        }
    }
}
