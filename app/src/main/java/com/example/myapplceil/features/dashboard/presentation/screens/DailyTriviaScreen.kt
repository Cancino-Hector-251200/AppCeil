package com.example.myapplceil.features.dashboard.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.core.theme.*

// Modelo de datos necesario
data class TriviaQuestion(
    val question: String,
    val options: List<String>,
    val correctOptionIndex: Int,
    val explanation: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyTriviaScreen(onBack: () -> Unit = {}, onFinish: () -> Unit = {}) {

    val triviaPool = listOf(
        TriviaQuestion("¿Es buena decisión financiera comprar comida chatarra barata para llenarte rápido?", listOf("Sí, porque soluciona el hambre hoy", "No, a la larga gastarás más en salud"), 1, "Explicación: La comida chatarra afecta tu salud, lo que genera gastos médicos futuros."),
        TriviaQuestion("¿Debes pagar primero una deuda con intereses o la suscripción de streaming?", listOf("La deuda con intereses", "La suscripción de streaming"), 0, "Explicación: Las deudas con intereses crecen como bola de nieve, detener esa pérdida es prioridad."),
        TriviaQuestion("¿Es mejor seguir comprando café diario o prepararlo en casa?", listOf("Seguir comprando fuera", "Prepararlo en casa y ahorrar"), 1, "Explicación: El café diario es un 'gasto hormiga'; parece poco al día, pero al mes es una cantidad importante."),
        TriviaQuestion("¿Comprar una oferta de ropa al 50% de descuento que no necesitas te hace ahorrar?", listOf("Sí, porque gastas la mitad", "No, porque gastas en algo que no te hace falta"), 1, "Explicación: El verdadero ahorro es no gastar en cosas que no necesitas."),
        TriviaQuestion("¿Es buena idea usar el fondo de emergencias para un teléfono nuevo por gusto?", listOf("Sí, es una emergencia emocional", "No, el fondo es para imprevistos reales"), 1, "Explicación: Los antojos no son emergencias; quedarás desprotegido ante problemas reales."),
        TriviaQuestion("¿Qué es financieramente más inteligente al recibir dinero extra?", listOf("Gastarlo en un gusto", "Abonar a deudas o ahorrar"), 1, "Explicación: Salir de deudas o ahorrar te da tranquilidad a largo plazo."),
        TriviaQuestion("¿Debes usar la tarjeta de crédito para cenar si ya no tienes presupuesto?", listOf("Sí, para eso está la tarjeta", "No, no deberías pedir prestado para consumo"), 1, "Explicación: Usar crédito para consumo que no puedes pagar cavará un hoyo financiero para el próximo mes."),
        TriviaQuestion("¿A qué deuda debes abonar primero si te sobra dinero?", listOf("A la pequeña para eliminarla", "Da igual, el dinero se acomoda"), 0, "Explicación: Eliminar la deuda pequeña rápidamente te da motivación y reduce el estrés."),
        TriviaQuestion("¿Qué pago elegir si tienes dinero disponible?", listOf("El pago mínimo", "El pago para no generar intereses"), 1, "Explicación: El pago para no generar intereses evita que el banco te cobre extras."),
        TriviaQuestion("¿Debes revisar si la mensualidad cabe en tu presupuesto antes de comprar a meses?", listOf("No, si es sin intereses es gratis", "Sí, compromete tus ingresos futuros"), 1, "Explicación: Acumular muchos 'meses sin intereses' puede terminar asfixiando tus ingresos."),
        TriviaQuestion("¿Debes usar las ganancias de tu negocio para comprar ropa cara de inmediato?", listOf("Sí, para demostrar éxito", "No, reinvierte en el negocio"), 1, "Explicación: Reinvertir permite que el negocio crezca y genere más dinero después."),
        TriviaQuestion("¿Qué es mejor para cumplir una meta de ahorro?", listOf("Separar una cantidad fija primero", "Ahorrar lo que sobre al final"), 0, "Explicación: 'Págate a ti mismo primero' asegura que cumplas tu meta antes de gastar."),
        TriviaQuestion("¿Es seguro un negocio que promete duplicar tu dinero sin hacer nada?", listOf("Sí, son oportunidades rápidas", "No, si suena demasiado bueno es probable que sea estafa"), 1, "Explicación: No existe el dinero fácil; rendimientos gigantes suelen ser fraude o estafa piramidal."),
        TriviaQuestion("¿Es mejor comprar material premium al iniciar un proyecto?", listOf("Sí, hay que empezar premium", "No, es mejor comprar lo básico y funcional"), 1, "Explicación: Es mejor validar tu idea con lo básico antes de arriesgar todo tu capital."),
        TriviaQuestion("¿Tu presupuesto debe calcularse con base en el mes alto o bajo?", listOf("Mes alto", "Mes promedio o bajo"), 1, "Explicación: Planificar con el mes bajo asegura que puedas cubrir tus necesidades básicas incluso en meses difíciles.")
    )

    // Cambia esto:
// val selectedTriviaSet by remember { mutableStateOf(triviaPool.shuffled().take(4)) }

// Por esto (usamos key para que se reinicie cada vez que la trivia se muestra):
    val selectedTriviaSet by remember(Unit) { mutableStateOf(triviaPool.shuffled().take(4)) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var showFeedback by remember { mutableStateOf(false) }

    val currentTrivia = selectedTriviaSet[currentIndex]

    Scaffold(
        containerColor = NavyDark,
        topBar = {
            TopAppBar(
                title = { Text("Pregunta ${currentIndex + 1} de 4", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyDark)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(currentTrivia.question, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(32.dp))

            currentTrivia.options.forEachIndexed { index, option ->
                val isSelected = selectedOption == index
                val containerColor = if (isSelected) MagentaNeon.copy(alpha = 0.3f) else CardDark

                Card(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp).clickable(enabled = !showFeedback) { selectedOption = index },
                    colors = CardDefaults.cardColors(containerColor = containerColor),
                    border = BorderStroke(1.dp, if (isSelected) MagentaNeon else Color.DarkGray)
                ) {
                    Text(option, color = Color.White, modifier = Modifier.padding(20.dp))
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            if (!showFeedback) {
                Button(
                    onClick = { showFeedback = true },
                    enabled = selectedOption != null,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon)
                ) {
                    Text("Comprobar", fontWeight = FontWeight.Bold)
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
        }
    }

    if (showFeedback) {
        ModalBottomSheet(onDismissRequest = {}, dragHandle = null, containerColor = CardDark) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(if (selectedOption == currentTrivia.correctOptionIndex) "¡Correcto!" else "Incorrecto",
                    color = if (selectedOption == currentTrivia.correctOptionIndex) GreenNeon else Color.Red, fontSize = 24.sp)
                Text(currentTrivia.explanation, color = Color.White, modifier = Modifier.padding(vertical = 16.dp))
                Button(
                    onClick = {
                        if (currentIndex < 3) {
                            currentIndex++
                            selectedOption = null
                            showFeedback = false
                        } else {
                            onFinish()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (currentIndex < 3) "Siguiente Pregunta" else "Finalizar Dinámica")
                }
            }
        }
    }
}
