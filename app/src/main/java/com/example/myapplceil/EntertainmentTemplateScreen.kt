package com.example.myapplceil

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.ui.theme.*

@Composable
fun EntertainmentTemplateScreen(onBack: () -> Unit = {}) {
    var step by remember { mutableIntStateOf(1) }
    var selectedCategory by remember { mutableStateOf("") }
    var selectedPlatform by remember { mutableStateOf("") }
    var selectedPlan by remember { mutableStateOf("") }
    var price by remember { mutableDoubleStateOf(0.0) }
    var date by remember { mutableStateOf("15/06/2024") }
    
    // For custom game flow
    var gameType by remember { mutableStateOf("") } // "Físico" or "Virtual"
    var gameName by remember { mutableStateOf("") }
    var virtualPlatform by remember { mutableStateOf("") }

    Scaffold(
        containerColor = NavyDark,
        topBar = { TemplateHeader("🎮 Entretenimiento", onBack) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            when (step) {
                1 -> item {
                    EntertainmentCategorySelector { category ->
                        selectedCategory = category
                        step = 2
                    }
                }
                2 -> {
                    when (selectedCategory) {
                        "Streaming" -> item {
                            StreamingPlatformFlow(
                                selectedPlatform = selectedPlatform,
                                onPlatformSelect = { selectedPlatform = it },
                                selectedPlan = selectedPlan,
                                onPlanSelect = { plan, p ->
                                    selectedPlan = plan
                                    price = p
                                },
                                onContinue = { step = 4 }
                            )
                        }
                        "Música" -> item {
                            MusicPlatformFlow(
                                selectedPlatform = selectedPlatform,
                                onPlatformSelect = { selectedPlatform = it },
                                selectedPlan = selectedPlan,
                                onPlanSelect = { plan, p ->
                                    selectedPlan = plan
                                    price = p
                                },
                                onContinue = { step = 4 }
                            )
                        }
                        "Videojuego" -> item {
                            GamingPlatformFlow(
                                selectedPlatform = selectedPlatform,
                                onPlatformSelect = { 
                                    selectedPlatform = it
                                    if (it == "Otro") step = 3
                                },
                                selectedPlan = selectedPlan,
                                onPlanSelect = { plan, p ->
                                    selectedPlan = plan
                                    price = p
                                },
                                onContinue = { step = 4 }
                            )
                        }
                        else -> item {
                            Text("Flujo no implementado", color = Color.White)
                        }
                    }
                }
                3 -> item {
                    CustomGameForm(
                        gameType = gameType,
                        onTypeSelect = { gameType = it },
                        gameName = gameName,
                        onNameChange = { gameName = it },
                        virtualPlatform = virtualPlatform,
                        onPlatformSelect = { virtualPlatform = it },
                        price = if (price == 0.0) "" else price.toString(),
                        onPriceChange = { price = it.toDoubleOrNull() ?: 0.0 },
                        onContinue = {
                            selectedPlatform = if (gameType == "Virtual") virtualPlatform else "Juego Físico"
                            selectedPlan = gameName
                            step = 4
                        }
                    )
                }
                4 -> {
                    item {
                        SubscriptionDatePicker(
                            date = date,
                            onDateChange = { date = it },
                            onContinue = { step = 5 }
                        )
                    }
                }
                5 -> {
                    item {
                        EntertainmentResult(
                            category = selectedCategory,
                            platform = selectedPlatform,
                            plan = selectedPlan,
                            price = price,
                            date = date
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EntertainmentCategorySelector(onCategorySelect: (String) -> Unit) {
    val categories = listOf(
        "🎬 Streaming" to "Streaming",
        "🎵 Música" to "Música",
        "🎮 Videojuego" to "Videojuego",
        "🎟 Eventos" to "Eventos",
        "🎧 Otro" to "Otro"
    )
    
    Text("¿Qué tipo de entretenimiento?", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(12.dp))
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        categories.forEach { (label, value) ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCategorySelect(value) },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = CardDark)
            ) {
                Text(label, color = Color.White, modifier = Modifier.padding(16.dp), fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun StreamingPlatformFlow(
    selectedPlatform: String,
    onPlatformSelect: (String) -> Unit,
    selectedPlan: String,
    onPlanSelect: (String, Double) -> Unit,
    onContinue: () -> Unit
) {
    val platforms = listOf("Netflix", "Amazon Prime Video", "Disney+", "Max", "Apple TV+", "Paramount+", "Crunchyroll", "Vix Premium", "Claro Video", "Otro")
    val plans = mapOf(
        "Netflix" to listOf("Básico" to 119.0, "Estándar" to 249.0, "Premium" to 329.0),
        "Disney+" to listOf("Estándar" to 219.0, "Premium" to 299.0)
    )

    Text("Selecciona la plataforma", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(12.dp))
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        platforms.forEach { platform ->
            SubscriptionCard(
                name = platform,
                isSelected = selectedPlatform == platform,
                onClick = { onPlatformSelect(platform) }
            ) {
                val platformPlans = plans[platform] ?: listOf("Plan Único" to 150.0)
                PlanSelector(
                    plans = platformPlans,
                    selectedPlan = selectedPlan,
                    onPlanSelect = onPlanSelect
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = onContinue,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon),
                    enabled = selectedPlan.isNotEmpty()
                ) {
                    Text("Continuar")
                }
            }
        }
    }
}

@Composable
fun MusicPlatformFlow(
    selectedPlatform: String,
    onPlatformSelect: (String) -> Unit,
    selectedPlan: String,
    onPlanSelect: (String, Double) -> Unit,
    onContinue: () -> Unit
) {
    val platforms = listOf("Spotify", "Apple Music", "Amazon Music", "YouTube Music", "Deezer", "Otro")
    val plans = mapOf(
        "Spotify" to listOf("Individual" to 129.0, "Estudiante" to 69.0, "Familiar" to 199.0)
    )

    Text("Selecciona el servicio de música", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(12.dp))
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        platforms.forEach { platform ->
            SubscriptionCard(
                name = platform,
                isSelected = selectedPlatform == platform,
                onClick = { onPlatformSelect(platform) }
            ) {
                val platformPlans = plans[platform] ?: listOf("Plan Individual" to 115.0)
                PlanSelector(
                    plans = platformPlans,
                    selectedPlan = selectedPlan,
                    onPlanSelect = onPlanSelect
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = onContinue,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon),
                    enabled = selectedPlan.isNotEmpty()
                ) {
                    Text("Continuar")
                }
            }
        }
    }
}

@Composable
fun GamingPlatformFlow(
    selectedPlatform: String,
    onPlatformSelect: (String) -> Unit,
    selectedPlan: String,
    onPlanSelect: (String, Double) -> Unit,
    onContinue: () -> Unit
) {
    val platforms = listOf("Xbox Game Pass", "PlayStation Plus", "Nintendo Switch Online", "EA Play", "Otro")
    val plans = mapOf(
        "Xbox Game Pass" to listOf("Core" to 169.0, "Ultimate" to 249.0, "PC Game Pass" to 149.0)
    )

    Text("Selecciona la plataforma de gaming", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(12.dp))
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        platforms.forEach { platform ->
            SubscriptionCard(
                name = platform,
                isSelected = selectedPlatform == platform,
                onClick = { onPlatformSelect(platform) }
            ) {
                if (platform != "Otro") {
                    val platformPlans = plans[platform] ?: listOf("Mensual" to 199.0)
                    PlanSelector(
                        plans = platformPlans,
                        selectedPlan = selectedPlan,
                        onPlanSelect = onPlanSelect
                    )
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = onContinue,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon),
                        enabled = selectedPlan.isNotEmpty()
                    ) {
                        Text("Continuar")
                    }
                }
            }
        }
    }
}

@Composable
fun CustomGameForm(
    gameType: String,
    onTypeSelect: (String) -> Unit,
    gameName: String,
    onNameChange: (String) -> Unit,
    virtualPlatform: String,
    onPlatformSelect: (String) -> Unit,
    price: String,
    onPriceChange: (String) -> Unit,
    onContinue: () -> Unit
) {
    Text("Configura tu videojuego", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(12.dp))
    
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("¿Qué tipo de juego es?", color = Color.Gray, fontSize = 14.sp)
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            listOf("Físico", "Virtual").forEach { type ->
                FilterChip(
                    selected = gameType == type,
                    onClick = { onTypeSelect(type) },
                    label = { Text(type) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MagentaNeon,
                        containerColor = CardDark,
                        labelColor = Color.White
                    )
                )
            }
        }
        
        if (gameType.isNotEmpty()) {
            if (gameType == "Virtual") {
                Text("Selecciona plataforma virtual", color = Color.Gray, fontSize = 14.sp)
                val vPlatforms = listOf("PlayStation Store", "Xbox Store", "Steam", "Epic Games", "Nintendo eShop", "Otra")
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    vPlatforms.chunked(2).forEach { row ->
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            row.forEach { p ->
                                FilterChip(
                                    selected = virtualPlatform == p,
                                    onClick = { onPlatformSelect(p) },
                                    label = { Text(p, fontSize = 12.sp) },
                                    modifier = Modifier.weight(1f),
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = MagentaNeon.copy(alpha = 0.2f),
                                        containerColor = CardDark
                                    )
                                )
                            }
                        }
                    }
                }
            }
            
            CeilTextField(label = "Nombre del juego", value = gameName, onValueChange = onNameChange, placeholder = "Ej. Elden Ring")
            CeilTextField(label = "Precio", value = price, onValueChange = onPriceChange, placeholder = "0.00", prefix = { Text("$ ", color = Color.White) })
            
            Button(
                onClick = onContinue,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon),
                enabled = gameName.isNotEmpty() && price.isNotEmpty() && (gameType == "Físico" || virtualPlatform.isNotEmpty())
            ) {
                Text("Continuar", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun SubscriptionDatePicker(
    date: String,
    onDateChange: (String) -> Unit,
    onContinue: () -> Unit
) {
    Text("Detalles de pago", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(12.dp))
    
    CeilTextField(
        label = "Fecha de cobro / compra", 
        value = date, 
        onValueChange = onDateChange, 
        placeholder = "DD/MM/AAAA",
        trailingIcon = { Icon(Icons.Default.CalendarToday, null, tint = MagentaNeon) }
    )
    
    Spacer(Modifier.height(24.dp))
    
    Button(
        onClick = onContinue,
        modifier = Modifier.fillMaxWidth().height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MagentaNeon)
    ) {
        Text("Crear apartado", fontWeight = FontWeight.Bold)
    }
}

@Composable
fun EntertainmentResult(
    category: String,
    platform: String,
    plan: String,
    price: Double,
    date: String
) {
    val icon = when (category) {
        "Streaming" -> "🎬"
        "Música" -> "🎵"
        "Videojuego" -> "🎮"
        else -> "🍿"
    }

    Text("Resultado", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(12.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(40.dp).background(MagentaNeon.copy(alpha = 0.1f), RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center) {
                    Text(icon, fontSize = 20.sp)
                }
                Spacer(Modifier.width(16.dp))
                Column {
                    Text(platform, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(plan, color = Color.Gray, fontSize = 14.sp)
                }
            }
            Spacer(Modifier.height(20.dp))
            Text("$$price", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Black)
            Spacer(Modifier.height(12.dp))
            Text("Próxima fecha: $date", color = Color.Gray, fontSize = 14.sp)
            Spacer(Modifier.height(16.dp))
            Surface(color = GreenNeon.copy(0.1f), shape = RoundedCornerShape(10.dp)) {
                Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("🔔 Recordatorio activo", color = GreenNeon, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
            Text("Te avisaremos 3 días antes", color = Color.Gray, fontSize = 11.sp, modifier = Modifier.padding(top = 4.dp))
        }
    }
}
