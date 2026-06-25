package com.example.myapplceil

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Solo dejamos RedNeon porque es el único que NO está en DashboardScreen.kt
// Los demás (NavyDark, CardDark, MagentaNeon, etc.) se toman automáticamente de DashboardScreen.kt
private val RedNeon = Color(0xFFEF4444)

@Composable
fun GraphicsScreen(onBack: () -> Unit = {}) {
    Scaffold(
        containerColor = NavyDark,
        topBar = {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = Color.White)
                    }
                    Text(
                        text = "Gráficas",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(onClick = { /* Settings */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Configuración", tint = Color.White)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Analiza tu progreso 🚀",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 12.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                
                // Selector de tiempo
                Surface(
                    color = CardDark,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Últimos 7 días", color = Color.White, fontSize = 14.sp)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.White)
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item { WeeklySummaryCard() }
            item { BarChartCard() }
            item { SavingProgressCard() }
            item { BalanceComparisonCard() }
            item { HabitCard() }
            item { CategoryCard() }
            item { ProgressMessageCard() }
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
fun WeeklySummaryCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardDark),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "Esta semana", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                SummaryItem("Ingreso", "$1500", GreenNeon)
                SummaryItem("Gastos", "$900", RedNeon)
                SummaryItem("Resultado", "+$600", GreenNeon, isResult = true)
            }
        }
    }
}

@Composable
fun SummaryItem(label: String, amount: String, color: Color, isResult: Boolean = false) {
    Column {
        Text(text = label, color = Color.Gray, fontSize = 12.sp)
        Text(
            text = (if (isResult && !amount.startsWith("-")) "🟢 " else "") + amount,
            color = color,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BarChartCard() {
    val days = listOf("L", "M", "X", "J", "V", "S", "D")
    val values = listOf(0.4f, 0.8f, 0.3f, 0.7f, 0.5f, 0.9f, 0.2f)

    Card(
        colors = CardDefaults.cardColors(containerColor = CardDark),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "Gastos por día", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                values.forEachIndexed { index, value ->
                    val animatedHeight by animateFloatAsState(
                        targetValue = value,
                        animationSpec = tween(durationMillis = 1000, delayMillis = index * 100),
                        label = "barAnimation"
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .width(12.dp)
                                .fillMaxHeight(animatedHeight)
                                .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                                .background(MagentaNeon)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = days[index], color = Color.Gray, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun SavingProgressCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardDark),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "Frecuencia de ahorro", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            
            SavingBar("Semana 1", "$100", 0.25f, PurpleNeon)
            SavingBar("Semana 2", "$250", 0.6f, PurpleNeon)
            SavingBar("Semana 3", "$400", 0.9f, PurpleNeon)
            
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "↑ Mejorando", color = GreenNeon, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun SavingBar(label: String, amount: String, progress: Float, color: Color) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = label, color = Color.LightGray, fontSize = 14.sp)
            Text(text = amount, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
            color = color,
            trackColor = Color.White.copy(alpha = 0.1f)
        )
    }
}

@Composable
fun BalanceComparisonCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardDark),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "Balance financiero", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            
            BalanceBar("Ingreso", "$1500", 1f, GreenNeon)
            BalanceBar("Gastos", "$900", 0.6f, RedNeon)
            BalanceBar("Ahorro", "$600", 0.4f, MagentaNeon)
        }
    }
}

@Composable
fun BalanceBar(label: String, amount: String, progress: Float, color: Color) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = label, color = Color.LightGray, fontSize = 14.sp)
            Text(text = amount, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth().height(12.dp).clip(RoundedCornerShape(6.dp)),
            color = color,
            trackColor = Color.White.copy(alpha = 0.1f)
        )
    }
}

@Composable
fun HabitCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardDark),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "Tus hábitos", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(text = "🔥 Días ahorrando", color = Color.Gray, fontSize = 12.sp)
                    Text(text = "7 días", color = YellowNeon, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "🍔 Gastos hormiga", color = Color.Gray, fontSize = 12.sp)
                    Row {
                        Text(text = "Antes: $300", color = Color.Gray, fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Ahora: $120", color = GreenNeon, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "🟢 Mejoraste 60%", color = GreenNeon, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun CategoryCard() {
    val categories = listOf(
        Triple("Comida", 0.4f, RedNeon),
        Triple("Transporte", 0.2f, Color.Cyan),
        Triple("Ocio", 0.25f, YellowNeon),
        Triple("Escuela", 0.15f, PurpleNeon)
    )
    
    Card(
        colors = CardDefaults.cardColors(containerColor = CardDark),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "Categorías de gasto", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            
            categories.forEach { (name, percent, color) ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(text = name, color = Color.White, fontSize = 14.sp, modifier = Modifier.width(100.dp))
                    Box(modifier = Modifier.weight(1f).height(8.dp).clip(RoundedCornerShape(4.dp)).background(Color.White.copy(alpha = 0.1f))) {
                        Box(modifier = Modifier.fillMaxWidth(percent).fillMaxHeight().clip(RoundedCornerShape(4.dp)).background(color))
                    }
                    Text(text = "${(percent * 100).toInt()}%", color = Color.White, fontSize = 14.sp, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}

@Composable
fun ProgressMessageCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = MagentaNeon.copy(alpha = 0.2f)),
        shape = RoundedCornerShape(20.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, MagentaNeon)
    ) {
        Row(
            modifier = Modifier.padding(20.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "🚀 Tu progreso", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = "Esta semana gastaste menos que la anterior",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
            Surface(
                color = MagentaNeon,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "+XP",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0B1120)
@Composable
fun GraphicsPreview() {
    GraphicsScreen()
}
