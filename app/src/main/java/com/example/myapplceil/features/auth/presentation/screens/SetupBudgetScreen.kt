package com.example.myapplceil.features.auth.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.core.theme.*

@Composable
fun SetupBudgetScreen(onSetupCompleto: (Double, Int) -> Unit) {
    var montoInput by remember { mutableStateOf("") }
    var semanasInput by remember { mutableStateOf("1") }
    var showError by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = NavyDark
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
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
            Text(
                text = "Ingresa tu dinero disponible para comenzar",
                fontSize = 14.sp,
                color = Color.LightGray,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = montoInput,
                onValueChange = { 
                    montoInput = it
                    showError = false 
                },
                label = { Text("Monto Inicial ($)", color = Color.White) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = showError,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = MagentaNeon,
                    unfocusedBorderColor = Color.LightGray,
                    errorBorderColor = Color.Red
                )
            )

            if (showError) {
                Text(
                    text = "Por favor ingresa un monto válido mayor a 0",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.Start).padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = semanasInput,
                onValueChange = { semanasInput = it },
                label = { Text("¿Para cuántas semanas?", color = Color.White) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = MagentaNeon,
                    unfocusedBorderColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    val monto = montoInput.toDoubleOrNull() ?: 0.0
                    val semanas = semanasInput.toIntOrNull() ?: 1
                    
                    if (monto > 0) {
                        onSetupCompleto(monto, semanas)
                    } else {
                        showError = true
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon),
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Comenzar a Registrar", 
                    fontSize = 18.sp, 
                    fontWeight = FontWeight.Bold, 
                    color = Color.White
                )
            }
        }
    }
}
