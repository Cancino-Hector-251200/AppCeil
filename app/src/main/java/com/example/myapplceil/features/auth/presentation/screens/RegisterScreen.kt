package com.example.myapplceil.features.auth.presentation.screens

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
import androidx.compose.material.icons.filled.Group
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.myapplceil.core.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit = {},
    onRegisterSuccess: (Boolean) -> Unit = {} 
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isAdmin by remember { mutableStateOf(false) }
    var expandedRol by remember { mutableStateOf(false) }
    
    val roles = listOf("Usuario", "Admin")
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NavyDark)
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
                        color = Color.White,
                        fontSize = 56.sp,
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
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MagentaNeon),
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

            Text(
                text = "La aventura comienza ahora",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Illustration
            Icon(
                imageVector = Icons.Outlined.Payments,
                contentDescription = null,
                tint = MagentaNeon,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Form Card
            Card(
                colors = CardDefaults.cardColors(containerColor = NavyLight),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // SECTOR DE ROL (Dropdown dinámico)
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Tipo de Usuario",
                            color = MagentaNeon,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        ExposedDropdownMenuBox(
                            expanded = expandedRol,
                            onExpandedChange = { expandedRol = !expandedRol }
                        ) {
                            TextField(
                                value = if (isAdmin) "Admin" else "Usuario",
                                onValueChange = {},
                                readOnly = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor()
                                    .clip(RoundedCornerShape(8.dp)),
                                leadingIcon = {
                                    Icon(imageVector = Icons.Default.Group, contentDescription = null, tint = NavyDark)
                                },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedRol) },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    disabledContainerColor = Color.White,
                                    focusedTextColor = Color.Black,
                                    unfocusedTextColor = Color.Black,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                )
                            )
                            ExposedDropdownMenu(
                                expanded = expandedRol,
                                onDismissRequest = { expandedRol = false },
                                modifier = Modifier.background(Color.White)
                            ) {
                                roles.forEach { rol ->
                                    DropdownMenuItem(
                                        text = { Text(rol, color = Color.Black) },
                                        onClick = {
                                            isAdmin = (rol == "Admin")
                                            expandedRol = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Username Field
                    RegisterInputField(
                        label = "Nombre",
                        value = username,
                        onValueChange = { username = it },
                        icon = Icons.Outlined.Person,
                        placeholder = "Tu nombre"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Email Field
                    RegisterInputField(
                        label = "Correo",
                        value = email,
                        onValueChange = { email = it },
                        icon = Icons.Outlined.Email,
                        placeholder = "ejemplo@correo.com",
                        keyboardType = KeyboardType.Email
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Password Field
                    RegisterInputField(
                        label = "Contraseña",
                        value = password,
                        onValueChange = { password = it },
                        icon = Icons.Outlined.Lock,
                        placeholder = "Crea una contraseña",
                        isPassword = true,
                        keyboardType = KeyboardType.Password
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Confirm Password Field
                    RegisterInputField(
                        label = "Confirma la contraseña",
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        icon = Icons.Outlined.Lock,
                        placeholder = "Repite tu contraseña",
                        isPassword = true,
                        keyboardType = KeyboardType.Password
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Action Button
                    Button(
                        onClick = { onRegisterSuccess(isAdmin) },
                        colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                    ) {
                        Text(
                            text = "Registrate",
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
                color = Color.White.copy(alpha = 0.8f),
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
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = MagentaNeon,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            leadingIcon = {
                Icon(imageVector = icon, contentDescription = null, tint = NavyDark)
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
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
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
