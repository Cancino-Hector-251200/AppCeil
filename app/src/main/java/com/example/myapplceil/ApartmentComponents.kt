package com.example.myapplceil

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProgressBar(progress: Float, color: Color = MagentaNeon) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = 1200),
        label = "progressBar"
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(14.dp)
                .clip(RoundedCornerShape(7.dp))
                .background(Color.White.copy(alpha = 0.05f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(animatedProgress)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(7.dp))
                    .background(color)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "${(progress * 100).toInt()}%",
                color = color,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun GoalProgressCard(
    title: String,
    goalAmount: Double,
    savedAmount: Double,
    color: Color = MagentaNeon
) {
    val progress = if (goalAmount > 0) (savedAmount / goalAmount).toFloat() else 0f
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = title, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Black)
            Spacer(modifier = Modifier.height(20.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(text = "Meta", color = Color.Gray, fontSize = 12.sp)
                    Text(text = "$${goalAmount.toInt()}", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "Ahorrado", color = Color.Gray, fontSize = 12.sp)
                    Text(text = "$${savedAmount.toInt()}", color = color, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
            
            Spacer(modifier = Modifier.height(20.dp))
            ProgressBar(progress = progress, color = color)
            
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Faltan: $${(goalAmount - savedAmount).coerceAtLeast(0.0).toInt()}",
                color = Color.LightGray,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun StreamingServiceCard(name: String, monthlyPrice: Double, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = if (isSelected) MagentaNeon.copy(alpha = 0.15f) else CardDark),
        border = if (isSelected) androidx.compose.foundation.BorderStroke(2.dp, MagentaNeon) else null
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = name, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = "Mensual: $$monthlyPrice MXN", color = Color.Gray, fontSize = 12.sp)
            }
            Text(text = "Anual: $${(monthlyPrice * 12).toInt()}", color = MagentaNeon, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun MusicServiceCard(name: String, monthlyPrice: Double, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = if (isSelected) PurpleNeon.copy(alpha = 0.15f) else CardDark),
        border = if (isSelected) androidx.compose.foundation.BorderStroke(2.dp, PurpleNeon) else null
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = name, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = "Mensual: $$monthlyPrice", color = Color.Gray, fontSize = 12.sp)
            }
            Text(text = "Anual: $${(monthlyPrice * 12).toInt()}", color = PurpleNeon, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun GameGoalCard(title: String, platform: String, goalAmount: Double, savedAmount: Double) {
    val progress = if (goalAmount > 0) (savedAmount / goalAmount).toFloat() else 0f
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(44.dp).clip(RoundedCornerShape(12.dp)).background(YellowNeon.copy(alpha = 0.2f)), contentAlignment = Alignment.Center) {
                    Text(text = "🎮", fontSize = 24.sp)
                }
                Spacer(Modifier.width(16.dp))
                Column {
                    Text(text = title, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(text = platform, color = Color.Gray, fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            ProgressBar(progress = progress, color = YellowNeon)
        }
    }
}

@Composable
fun SchoolBudgetCard(category: String, amount: Double, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark.copy(alpha = 0.5f))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(8.dp).clip(androidx.compose.foundation.shape.CircleShape).background(color))
            Spacer(Modifier.width(12.dp))
            Text(text = category, color = Color.White, modifier = Modifier.weight(1f))
            Text(text = "$${amount.toInt()}", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun FoodBudgetCard(title: String, budget: Double, spent: Double) {
    val progress = if (budget > 0) (spent / budget).toFloat() else 0f
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(24.dp), colors = CardDefaults.cardColors(containerColor = CardDark)) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = title, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(text = "Presupuesto", color = Color.Gray, fontSize = 12.sp)
                    Text(text = "$$budget", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "Gastado", color = Color.Gray, fontSize = 12.sp)
                    Text(text = "$$spent", color = GreenNeon, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            ProgressBar(progress = progress, color = GreenNeon)
        }
    }
}

@Composable
fun HomeExpenseCard(title: String, amount: Double, icon: String) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = CardDark)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).background(PurpleNeon.copy(alpha = 0.15f)), contentAlignment = Alignment.Center) {
                Text(text = icon, fontSize = 20.sp)
            }
            Spacer(Modifier.width(16.dp))
            Text(text = title, color = Color.White, modifier = Modifier.weight(1f))
            Text(text = "$$amount", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun TemplateHeader(title: String, onBack: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 16.dp), verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onBack) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = Color.White)
        }
        Text(text = title, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun CeilTextField(
    label: String, 
    value: String, 
    onValueChange: (String) -> Unit, 
    placeholder: String, 
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, color = Color.LightGray, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
        OutlinedTextField(
            value = value, onValueChange = onValueChange, placeholder = { Text(placeholder, color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                focusedBorderColor = MagentaNeon, unfocusedBorderColor = Color.Gray,
                focusedContainerColor = CardDark, unfocusedContainerColor = CardDark
            ),
            trailingIcon = trailingIcon,
            prefix = prefix
        )
    }
}
