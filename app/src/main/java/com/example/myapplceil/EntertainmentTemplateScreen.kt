package com.example.myapplceil

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.myapplceil.ui.theme.*

@Composable
fun EntertainmentTemplateScreen(onBack: () -> Unit = {}) {
    var selectedType by remember { mutableStateOf("Streaming") }
    var selectedService by remember { mutableStateOf("") }
    
    val types = listOf("🎮 Videojuego", "🎬 Streaming", "🎵 Música", "🎟 Eventos")
    
    val streamingServices = listOf(
        "Netflix" to 249.0, 
        "Amazon Prime" to 99.0, 
        "Disney+" to 219.0, 
        "Max" to 149.0, 
        "Apple TV+" to 129.0, 
        "Crunchyroll" to 119.0,
        "Paramount+" to 109.0, 
        "Vix Premium" to 119.0, 
        "Claro Video" to 115.0
    )

    val musicServices = listOf(
        "Spotify Premium" to 129.0, 
        "Apple Music" to 129.0, 
        "YouTube Music" to 139.0, 
        "Amazon Music Unlimited" to 129.0, 
        "Deezer" to 129.0
    )

    Scaffold(
        containerColor = NavyDark,
        topBar = { TemplateHeader("🎮 Entretenimiento", onBack) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text("¿Qué deseas planear?", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    types.forEach { type ->
                        val typeKey = when {
                            type.contains("Streaming") -> "Streaming"
                            type.contains("Música") -> "Música"
                            type.contains("Videojuego") -> "Videojuego"
                            else -> "Eventos"
                        }
                        FilterChip(
                            selected = selectedType == typeKey,
                            onClick = { 
                                selectedType = typeKey
                                selectedService = "" 
                            },
                            label = { Text(type, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MagentaNeon,
                                containerColor = CardDark,
                                labelColor = Color.Gray,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }

            when (selectedType) {
                "Streaming" -> {
                    items(streamingServices) { (name, price) ->
                        StreamingServiceCard(
                            name = name,
                            monthlyPrice = price,
                            isSelected = selectedService == name,
                            onClick = { selectedService = name }
                        )
                    }
                }
                "Música" -> {
                    items(musicServices) { (name, price) ->
                        MusicServiceCard(
                            name = name,
                            monthlyPrice = price,
                            isSelected = selectedService == name,
                            onClick = { selectedService = name }
                        )
                    }
                }
                "Videojuego" -> {
                    item {
                        CeilTextField(label = "Nombre del juego", value = "GTA VI", onValueChange = {}, placeholder = "Ej. GTA VI")
                        Spacer(Modifier.height(12.dp))
                        CeilTextField(label = "Precio estimado", value = "1500", onValueChange = {}, placeholder = "$")
                        Spacer(Modifier.height(20.dp))
                        GameGoalCard(title = "GTA VI", platform = "PlayStation 5", goalAmount = 1500.0, savedAmount = 500.0)
                    }
                }
                else -> {
                    item {
                        CeilTextField(label = "Nombre del evento", value = "", onValueChange = {}, placeholder = "Ej. Concierto")
                        Spacer(Modifier.height(12.dp))
                        CeilTextField(label = "Presupuesto estimado", value = "", onValueChange = {}, placeholder = "$")
                    }
                }
            }

            item {
                Button(
                    onClick = { /* Acción UI */ },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Configurar entretenimiento", fontWeight = FontWeight.Bold, color = Color.White)
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}
