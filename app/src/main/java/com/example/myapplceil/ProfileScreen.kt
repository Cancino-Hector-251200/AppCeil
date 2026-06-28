package com.example.myapplceil

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigateBack: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    // Variables de estado
    var nombre by remember { mutableStateOf("Usuario Ceil") }
    var edad by remember { mutableStateOf("20") }
    var sexo by remember { mutableStateOf("Masculino") }
    var correo by remember { mutableStateOf("usuario@example.com") }
    
    val opcionesSexo = listOf("Masculino", "Femenino", "Otro")
    var expandedSexo by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyDark)
            )
        },
        containerColor = NavyDark
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- SECCIÓN: FOTO DE PERFIL CON BOTÓN DE CAMBIO ---
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                // Avatar Circle
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(BorderStroke(3.dp, MagentaNeon), CircleShape)
                        .clip(CircleShape)
                        .background(NavyLight),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Foto de perfil",
                        modifier = Modifier.size(80.dp),
                        tint = Color.White
                    )
                }
                
                // Camera Icon Button (Para "agregar foto")
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MagentaNeon)
                        .clickable { /* Aquí iría la lógica para abrir la galería/cámara */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Cambiar foto",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Nivel 5 - Ahorrador Experto",
                color = MagentaNeon,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- CAMPOS EDITABLES ---

            // Card: Nombre
            ProfileCardField(
                label = "Nombre y Apellidos",
                value = nombre,
                onValueChange = { nombre = it }
            )

            // Card: Edad
            ProfileCardField(
                label = "Edad",
                value = edad,
                onValueChange = { edad = it },
                isNumber = true
            )

            // Card: Sexo (CON MENÚ DESPLEGABLE)
            ExposedDropdownMenuBox(
                expanded = expandedSexo,
                onExpandedChange = { expandedSexo = !expandedSexo },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    colors = CardDefaults.cardColors(containerColor = NavyLight),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                        Text(text = "Sexo", color = Color.LightGray, fontSize = 12.sp)
                        TextField(
                            value = sexo,
                            onValueChange = {},
                            readOnly = true, // Solo lectura para forzar el uso del menú
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedSexo) },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedTrailingIconColor = MagentaNeon,
                                unfocusedTrailingIconColor = MagentaNeon
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                ExposedDropdownMenu(
                    expanded = expandedSexo,
                    onDismissRequest = { expandedSexo = false },
                    modifier = Modifier.background(NavyLight)
                ) {
                    opcionesSexo.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion, color = Color.White) },
                            onClick = {
                                sexo = opcion
                                expandedSexo = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }

            // Card: Correo
            ProfileCardField(
                label = "Correo Electrónico",
                value = correo,
                onValueChange = { correo = it }
            )

            // --- SECCIÓN DE PROGRESO ---
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = NavyLight),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Meta Mensual", color = Color.White, fontSize = 14.sp)
                        Text("70%", color = MagentaNeon, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { 0.7f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = MagentaNeon,
                        trackColor = Color.White.copy(alpha = 0.1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- BOTONES DE ACCIÓN ---
            Button(
                onClick = { /* Lógica para guardar cambios */ },
                colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Guardar Cambios", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onLogout) {
                Text("Cerrar Sesión", color = Color.Gray, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun ProfileCardField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isNumber: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = NavyLight),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Text(text = label, color = Color.LightGray, fontSize = 12.sp)
            TextField(
                value = value,
                onValueChange = onValueChange,
                keyboardOptions = if (isNumber) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions.Default,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = MagentaNeon,
                    unfocusedIndicatorColor = Color.DarkGray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = MagentaNeon
                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    MyApplCeilTheme {
        ProfileScreen()
    }
}
