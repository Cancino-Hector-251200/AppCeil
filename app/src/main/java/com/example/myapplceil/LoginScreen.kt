package com.example.myapplceil

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.ui.theme.* // Asegúrate de que apunte a tu paleta

@Composable
fun LoginScreen(onNavigateToRegister: () -> Unit = {}, onNavigateToDashboard: () -> Unit = {}) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundScreen // 1. Fondo claro aplicado aquí
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // 2. SECCIÓN DE MARCA (LOGO CEIL)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "CEIL",
                    fontSize = 54.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = BlueDark, // Letras oscuras para que contrasten en el fondo claro
                )
                Spacer(modifier = Modifier.width(4.dp))
                Surface(
                    shape = RoundedCornerShape(50),
                    color = PinkMain, // Circulo magenta
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("$", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Spacer(modifier = Modifier.height(8.dp))

            // Eslogan
            Text(
                text = "Sube de nivel y tus finanzas tambien",
                color = Color.DarkGray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 3. ILUSTRACIÓN (Cohete)
            Icon(
                imageVector = Icons.Default.RocketLaunch,
                contentDescription = "Rocket",
                tint = PinkMain, // Cohete en color neón/magenta
                modifier = Modifier.size(72.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 4. TARJETA DEL FORMULARIO
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White), // Tarjeta blanca
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp) // Sombra suave
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    // Campo: Correo
                    Text("Correo", color = PinkMain, fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 16.dp),
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = BlueDark) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PinkMain,
                            unfocusedBorderColor = Color.LightGray,
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true
                    )

                    // Campo: Contraseña
                    Text("Contraseña", color = PinkMain, fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 32.dp),
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = BlueDark) },
                        visualTransformation = PasswordVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PinkMain,
                            unfocusedBorderColor = Color.LightGray,
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true
                    )

                    // 5. BOTONES Y ENLACES
                    Button(
                        onClick = onNavigateToDashboard,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PinkMain), // Botón principal magenta
                        shape = RoundedCornerShape(25.dp)
                    ) {
                        Text("Iniciar Sesion", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Link de Registro
                    TextButton(
                        onClick = onNavigateToRegister,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "No tienes cuenta?, registrate aquí",
                            color = BlueDark, // Link en azul oscuro
                            textDecoration = TextDecoration.Underline,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}