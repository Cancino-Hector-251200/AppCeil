package com.example.myapplceil.ui.features.admin

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.core.theme.*

@Composable
fun AdminStatCard(
    title: String,
    value: String,
    icon: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = icon, fontSize = 24.sp)
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(color)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = value, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Black)
            Text(text = title, color = Color.Gray, fontSize = 12.sp)
        }
    }
}

@Composable
fun AdminProgressBar(
    label: String,
    progress: Float,
    color: Color
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(1500),
        label = "progress"
    )

    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, color = Color.White, fontSize = 14.sp)
            Text(text = "${(progress * 100).toInt()}%", color = color, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.White.copy(alpha = 0.05f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(animatedProgress)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(4.dp))
                    .background(color)
            )
        }
    }
}

@Composable
fun SimulatedHistogram(
    data: List<Float>,
    color: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().height(150.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        data.forEach { value ->
            val animatedHeight by animateFloatAsState(
                targetValue = value,
                animationSpec = tween(1000),
                label = "bar"
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(animatedHeight)
                    .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                    .background(color.copy(alpha = 0.8f))
            )
        }
    }
}

@Composable
fun AdminSectionHeader(title: String, subtitle: String? = null) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
        Text(text = title, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        if (subtitle != null) {
            Text(text = subtitle, color = Color.Gray, fontSize = 14.sp)
        }
    }
}

@Composable
fun AdminChartCard(
    title: String,
    description: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = description, color = Color.Gray, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(20.dp))
            content()
        }
    }
}
