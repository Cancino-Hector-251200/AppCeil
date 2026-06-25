package com.example.myapplceil

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.ui.theme.*

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit = {},
    onNavigateToDashboard: () -> Unit = {}
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(Color(0xFF9C27B0), MagentaNeon)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundScreen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Logo Section
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "CEIL",
                    style = TextStyle(
                        color = BlueDark,
                        fontSize = 56.sp,
                        fontWeight = FontWeight.Black,
                        shadow = Shadow(
                            color = PinkMain.copy(alpha = 0.5f),
                            offset = Offset(4f, 4f),
                            blurRadius = 0f
                        )
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(PinkMain),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Illustration
            Icon(
                imageVector = Icons.Outlined.Payments,
                contentDescription = null,
                tint = PinkMain,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Form Card
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Regístrate con nosotros",
                        color = Color.Gray,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    // Username Field
                    RegisterInputField(
                        label = "Usuario",
                        value = username,
                        onValueChange = { username = it },
                        icon = Icons.Outlined.Person,
                        placeholder = "Usuario"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Email Field
                    RegisterInputField(
                        label = "Correo",
                        value = email,
                        onValueChange = { email = it },
                        icon = Icons.Outlined.Email,
                        placeholder = "Correo"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Password Field
                    RegisterInputField(
                        label = "Contraseña",
                        value = password,
                        onValueChange = { password = it },
                        icon = Icons.Outlined.Lock,
                        placeholder = "Contraseña",
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Action Button
                    Button(
                        onClick = { onNavigateToDashboard() },
                        colors = ButtonDefaults.buttonColors(containerColor = PinkMain),
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                    ) {
                        Text(
                            text = "Registrarse",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Link to Login
            Text(
                text = "¿Ya tienes cuenta?, inicia sesión aquí",
                color = BlueDark.copy(alpha = 0.7f),
                textDecoration = TextDecoration.Underline,
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .clickable { onNavigateToLogin() }
            )
        }
    }

}

@Composable
fun RegisterInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    placeholder: String,
    isPassword: Boolean = false
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = PinkMain,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = {
                Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
            },
            placeholder = { Text(text = placeholder, color = Color.Gray) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PinkMain,
                unfocusedBorderColor = Color.LightGray,
            ),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
            keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
            singleLine = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    MyApplCeilTheme {
        RegisterScreen()
    }
}
