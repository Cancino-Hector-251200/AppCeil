package com.example.myapplceil

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import com.example.myapplceil.ui.theme.*

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
fun ApartmentSummaryCard(
    title: String = "Apartado",
    budget: Double,
    spent: Double,
    available: Double
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = title, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "Presupuesto", color = Color.Gray, fontSize = 14.sp)
                    Text(text = "$${budget.toInt()}", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "Disponible", color = Color.Gray, fontSize = 14.sp)
                    Text(text = "$${available.toInt()}", color = GreenNeon, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            val progress = if (budget > 0) (spent / budget).toFloat() else 0f
            ProgressBar(progress = progress, color = PurpleNeon)

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Gastado: $${spent.toInt()}", color = Color.LightGray, fontSize = 14.sp)
                Text(text = "${(progress * 100).toInt()}%", color = PurpleNeon, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ExpenseItem(expense: ApartmentExpense) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.05f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = expense.emoji, fontSize = 20.sp)
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Text(
                text = expense.name,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
            
            Text(
                text = "$${expense.amount.toInt()}",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TemplateCard(icon: String, name: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.width(130.dp).height(150.dp).clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(50.dp).background(MagentaNeon.copy(alpha = 0.1f), RoundedCornerShape(15.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(icon, fontSize = 28.sp)
            }
            Spacer(Modifier.height(12.dp))
            Text(name, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ApartmentCard(name: String, icon: ImageVector, budget: Double, spent: Double, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(24.dp))
                Spacer(Modifier.width(12.dp))
                Text(name, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(16.dp))
            val progress = (spent / budget).toFloat()
            ProgressBar(progress, color)
            Spacer(Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Gastado: $${spent.toInt()}", color = Color.Gray, fontSize = 12.sp)
                Text("Meta: $${budget.toInt()}", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ExpenseCard(emoji: String, name: String, amount: Double) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = emoji, fontSize = 24.sp)
            Spacer(Modifier.width(16.dp))
            Text(text = name, color = Color.White, modifier = Modifier.weight(1f), fontSize = 16.sp)
            Text(text = "$${amount.toInt()}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@Composable
fun EmojiSelector(selectedEmoji: String, onEmojiSelected: (String) -> Unit) {
    val emojis = listOf("😀", "🍔", "🍕", "☕", "🚕", "⛽", "🎮", "🎬", "📚", "🖨", "🛒", "💻", "🏠", "💡", "🎁")
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        modifier = Modifier.height(150.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(emojis) { emoji ->
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(if (selectedEmoji == emoji) MagentaNeon else Color.White.copy(alpha = 0.05f))
                    .clickable { onEmojiSelected(emoji) },
                contentAlignment = Alignment.Center
            ) {
                Text(text = emoji, fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun SubscriptionCard(name: String, isSelected: Boolean, onClick: () -> Unit, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().animateContentSize().clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark),
        border = if (isSelected) BorderStroke(2.dp, MagentaNeon) else null
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = name, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            if (isSelected) {
                Spacer(modifier = Modifier.height(12.dp))
                content()
            }
        }
    }
}

@Composable
fun PlanSelector(plans: List<Pair<String, Double>>, selectedPlan: String, onPlanSelect: (String, Double) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        plans.forEach { (planName, price) ->
            val isSelected = selectedPlan == planName
            Card(
                modifier = Modifier.fillMaxWidth().clickable { onPlanSelect(planName, price) },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = if (isSelected) MagentaNeon.copy(alpha = 0.1f) else Color.White.copy(alpha = 0.05f)),
                border = if (isSelected) BorderStroke(1.dp, MagentaNeon) else null
            ) {
                Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = planName, color = Color.White, fontSize = 14.sp)
                    Text(text = "$$price / mes", color = if (isSelected) MagentaNeon else Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun SchoolTypeSelector(selectedType: String, onTypeSelected: (String) -> Unit) {
    val types = listOf("Proyecto de materia", "Exposición", "Investigación", "Reporte académico", "Proyecto final", "Práctica", "Otro")
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        types.forEach { type ->
            val isSelected = selectedType == type
            FilterChip(
                selected = isSelected,
                onClick = { onTypeSelected(type) },
                label = { Text(type) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MagentaNeon,
                    labelColor = Color.White,
                    selectedLabelColor = Color.White
                )
            )
        }
    }
}

@Composable
fun TemplateHeader(title: String, onBack: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
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
fun SchoolBudgetCard(category: String, amount: Double, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark.copy(alpha = 0.5f))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(color))
            Spacer(Modifier.width(12.dp))
            Text(text = category, color = Color.White, modifier = Modifier.weight(1f))
            Text(text = "$${amount.toInt()}", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}
