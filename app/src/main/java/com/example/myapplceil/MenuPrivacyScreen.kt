package com.example.myapplceil

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.ui.theme.*

// Color adicional solicitado por el usuario si no está en el tema
val CardDarkLocal = Color(0xFF172033)
val LightGrayLocal = Color(0xFFD3D3D3)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuPrivacyScreen(
    onNavigateBack: () -> Unit = {},
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Términos Legales", 
                        color = Color.White, 
                        fontWeight = FontWeight.Bold 
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NavyDark
                )
            )
        },
        containerColor = NavyDark
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- ÍCONO PRINCIPAL ---
            Icon(
                imageVector = Icons.Default.PrivacyTip,
                contentDescription = null,
                tint = MagentaNeon,
                modifier = Modifier.size(72.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- TÍTULO ---
            Text(
                text = "Aviso de Privacidad",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- CONTENEDOR DEL TEXTO LEGAL (CARD) ---
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CardDarkLocal)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .verticalScroll(scrollState)
                ) {
                    Text(
                        text = """
                            Bienvenido a CEIL. Tu privacidad y la seguridad de tus finanzas son nuestra prioridad absoluta. Este documento describe cómo manejamos tus datos.
                            
                            1. Recopilación de Datos:
                            CEIL recolecta información financiera básica ingresada por el usuario, como montos de presupuesto, deudas y metas de ahorro. Estos datos son esenciales para proporcionar los cálculos de presupuesto diario y el seguimiento de metas.
                            
                            2. Almacenamiento y Seguridad:
                            Toda la información personal y financiera se almacena de forma segura utilizando los servicios de Amazon Web Services (AWS). Implementamos encriptación de datos tanto en tránsito como en reposo para asegurar que nadie más que tú tenga acceso a tu información.
                            
                            3. Uso de la Información:
                            Los datos recopilados se utilizan exclusivamente para:
                            - Calcular y optimizar tu presupuesto diario.
                            - Proporcionar gráficas de rendimiento financiero.
                            - Gestionar tus recordatorios de deudas.
                            - Ofrecer retos de ahorro personalizados.
                            
                            4. Terceros:
                            No vendemos, intercambiamos ni transferimos tus datos a terceros con fines publicitarios. Solo utilizamos proveedores de servicios críticos (como AWS) para el funcionamiento técnico de la aplicación.
                            
                            5. Control del Usuario:
                            Puedes editar o eliminar tus datos en cualquier momento desde la sección de perfil y configuración. Al eliminar tu cuenta, todos tus registros financieros serán borrados permanentemente de nuestros servidores.
                            
                            Al utilizar CEIL, aceptas los términos descritos en este aviso de privacidad. Nos reservamos el derecho de actualizar este documento para reflejar cambios en nuestras prácticas o por requisitos legales.
                        """.trimIndent(),
                        color = LightGrayLocal,
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPrivacyPreview() {
    MyApplCeilTheme {
        MenuPrivacyScreen()
    }
}
