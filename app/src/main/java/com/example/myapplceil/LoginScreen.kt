package com.example.myapplceil

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.RocketLaunch
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
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
fun LoginScreen(
    onNavigateToRegister: () -> Unit = {},
    onNavigateToDashboard: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NavyDark)
    ) {
        // Background Decorative Element (Semicircle/Curve)
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = NavyLight,
                radius = size.width,
                center = Offset(size.width / 2, size.height * 1.1f)
            )
        }

        // Top Left Login Label
        Text(
            text = "LOGIN",
            color = Color.White.copy(alpha = 0.3f),
            fontSize = 12.sp,
            modifier = Modifier.padding(16.dp).statusBarsPadding()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo Section
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "CEIL",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Black,
                        shadow = Shadow(
                            color = MagentaNeon,
                            offset = Offset(8f, 8f),
                            blurRadius = 0f
                        )
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MagentaNeon),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Text(
                text = "Sube de nivel y tus finanzas tambien",
                color = Color.White,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Rocket Icon
            Icon(
                imageVector = Icons.Outlined.RocketLaunch,
                contentDescription = null,
                tint = MagentaNeon,
                modifier = Modifier.size(60.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Form Card
            Card(
                colors = CardDefaults.cardColors(containerColor = NavyLight),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    // Email Field
                    Text(
                        text = "Correo",
                        color = MagentaNeon,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    CustomTextField(
                        value = email,
                        onValueChange = { email = it },
                        icon = Icons.Outlined.Email,
                        placeholder = "ejemplo@correo.com"
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Password Field
                    Text(
                        text = "Contraseña",
                        color = MagentaNeon,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    CustomTextField(
                        value = password,
                        onValueChange = { password = it },
                        icon = Icons.Outlined.Lock,
                        isPassword = true,
                        placeholder = "********"
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Login Button
                    Button(
                        onClick = { onNavigateToDashboard() },
                        colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        Text(
                            text = "Iniciar Sesion",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Register Link
                    Text(
                        text = "No tienes cuenta?, registrate aquí",
                        color = Color.White.copy(alpha = 0.8f),
                        textDecoration = TextDecoration.Underline,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable { onNavigateToRegister() }
                    )
                }
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    placeholder: String,
    isPassword: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
        },
        placeholder = { Text(text = placeholder, color = Color.Gray) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        ),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    MyApplCeilTheme {
        LoginScreen()
    }
}
