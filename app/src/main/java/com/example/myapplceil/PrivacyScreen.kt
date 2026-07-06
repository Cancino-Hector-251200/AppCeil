package com.example.myapplceil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PrivacyTip
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

@Composable
fun PrivacyScreen(
    onAceptarTerms: () -> Unit = {},
    onRechazarTerms: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NavyDark)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.PrivacyTip,
                contentDescription = null,
                tint = MagentaNeon,
                modifier = Modifier.size(64.dp)
                modifier = Modifier.size(72.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Aviso de Privacidad",
                color = Color.White,
                fontSize = 28.sp,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = NavyLight),
                shape = RoundedCornerShape(24.dp),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "En CEIL, nos tomamos muy en serio la seguridad de tus finanzas y datos personales. " +
                            "Al aceptar estos términos, permites que la aplicación gestione tu presupuesto diario " +
                            "de manera local y optimice tus hábitos de ahorro mediante retos gamificados. " +
                            "No compartimos tu información con terceros sin tu consentimiento explícito.",
                    color = Color.LightGray,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp)
                    modifier = Modifier.padding(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = onAceptarTerms,
                colors = ButtonDefaults.buttonColors(containerColor = PinkMain),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .height(55.dp)
            ) {
                Text(
                    text = "Aceptar",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = onRechazarTerms,
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = androidx.compose.ui.graphics.SolidColor(Color.Gray)
                ),
                shape = RoundedCornerShape(25.dp),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .height(55.dp)
            ) {
                Text(
                    text = "Rechazar",
                    color = Color.Gray
                    color = Color.LightGray,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrivacyPreview() {
    MyApplCeilTheme {
        PrivacyScreen()
    }
}
