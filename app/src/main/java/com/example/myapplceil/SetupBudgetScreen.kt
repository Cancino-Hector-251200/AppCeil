package com.example.myapplceil 
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.ui.theme.* // Importa tus colores personalizados

@Composable
fun SetupBudgetScreen(onSetupCompleto: (Double, Int) -> Unit) {
    var montoInput by remember { mutableStateOf("") }
    var semanasInput by remember { mutableStateOf("1") }

    // Surface asegura que el fondo sea el gris/blanco claro de tu Light Mode
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundScreen
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Configura tus Finanzas",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = BlueDark // Título principal en azul marino
            )
            Text(
                text = "Ingresa tu dinero disponible y el tiempo que planeas administrarlo",
                fontSize = 14.sp,
                color = Color.DarkGray, // Gris oscuro para que se lea en fondo claro
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = montoInput,
                onValueChange = { montoInput = it },
                label = { Text("Monto Inicial ($)", color = BlueDark) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PinkMain, // La línea se vuelve magenta al escribir
                    unfocusedBorderColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = semanasInput,
                onValueChange = { semanasInput = it },
                label = { Text("¿Para cuántas semanas es?", color = BlueDark) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PinkMain,
                    unfocusedBorderColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    val monto = montoInput.toDoubleOrNull() ?: 0.0
                    val semanas = semanasInput.toIntOrNull() ?: 1
                    if (monto > 0) {
                        onSetupCompleto(monto, semanas)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = PinkMain), // Botón fucsia brillante
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Comenzar a Registrar", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}