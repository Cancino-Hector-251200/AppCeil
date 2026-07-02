package com.example.myapplceil

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.ui.theme.*

data class Medal(
    val id: Int,
    val name: String,
    val description: String,
    val emoji: String,
    val isUnlocked: Boolean,
    val unlockDate: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedalsScreen(onBack: () -> Unit = {}) {
    var selectedMedal by remember { mutableStateOf<Medal?>(null) }
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    // Lista de medallas actualizada: se eliminó "Planificador" e "Inversionista"
    // para cumplir exactamente con la lista de logros solicitada.
    val medals = listOf<Medal>(
        Medal(1, "Primer ahorro", "¡Felicidades por tu primer peso guardado!", "💰", true, "12/05/2024"),
        Medal(2, "Control total", "Registraste todos tus gastos por una semana.", "📝", true, "15/05/2024"),
        Medal(3, "Cazador de gastos hormiga", "Detectaste y redujiste 3 gastos hormiga.", "🐜", true, "18/05/2024"),
        Medal(4, "Meta cumplida", "Llegaste al 100% de tu objetivo de ahorro.", "🎯", true, "20/05/2024"),
        Medal(5, "Estudiante organizado", "Asignaste presupuesto a tus proyectos escolares.", "📚", true, "22/05/2024"),
        Medal(6, "Constante", "Usaste la app por 7 días seguidos.", "🔥", false)
    )

    Scaffold(
        containerColor = NavyDark,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = Color.White)
                }
                Text(
                    text = "Medallas",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            ProgressMedalCard()

            Spacer(modifier = Modifier.height(24.dp))

            SectionHeader(title = "Mis logros")

            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(medals) { medal ->
                    MedalCard(medal = medal) {
                        selectedMedal = medal
                        showBottomSheet = true
                    }
                }
            }
        }

        if (showBottomSheet && selectedMedal != null) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                containerColor = CardDark,
                dragHandle = { BottomSheetDefaults.DragHandle(color = Color.Gray) }
            ) {
                MedalDetailSheet(medal = selectedMedal!!)
            }
        }
    }
}

@Composable
fun ProgressMedalCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "🏆 Tu progreso",
                color = MagentaNeon,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Nivel financiero
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("🌱", fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Nivel financiero", color = Color.Gray, fontSize = 12.sp)
                    }
                    Text(
                        text = "Explorador",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Racha
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("🔥", fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Racha", color = Color.Gray, fontSize = 12.sp)
                    }
                    Text(
                        text = "7 días",
                        color = YellowNeon,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Medallas
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("🏆", fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Medallas", color = Color.Gray, fontSize = 12.sp)
                    }
                    Text(
                        text = "5/6",
                        color = GreenNeon,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun MedalCard(medal: Medal, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (medal.isUnlocked) CardDark else CardDark.copy(alpha = 0.5f)
        ),
        border = if (!medal.isUnlocked) BorderStroke(1.dp, Color.White.copy(alpha = 0.1f)) else null
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(
                            if (medal.isUnlocked) MagentaNeon.copy(alpha = 0.1f) 
                            else Color.Black.copy(alpha = 0.2f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = medal.emoji,
                        fontSize = 32.sp,
                        modifier = Modifier.alpha(if (medal.isUnlocked) 1f else 0.4f)
                    )
                }
                if (!medal.isUnlocked) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(18.dp)
                            .align(Alignment.BottomEnd)
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = medal.name,
                color = if (medal.isUnlocked) Color.White else Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
            Text(
                text = medal.description,
                color = Color.Gray,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                lineHeight = 14.sp,
                maxLines = 2
            )
        }
    }
}

@Composable
fun MedalDetailSheet(medal: Medal) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .padding(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = medal.emoji, fontSize = 80.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = medal.name,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = medal.description,
            color = Color.Gray,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        
        if (medal.isUnlocked) {
            Surface(
                color = GreenNeon.copy(alpha = 0.1f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Desbloqueada el ${medal.unlockDate}",
                    color = GreenNeon,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        } else {
            Text(
                text = "¡Sigue usando CEIL para desbloquear este logro!",
                color = MagentaNeon,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}
