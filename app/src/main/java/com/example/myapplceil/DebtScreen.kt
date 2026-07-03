package com.example.myapplceil

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplceil.ui.theme.*

@Composable
fun DebtScreen(onBack: () -> Unit = {}) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = NavyDark,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = MagentaNeon,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier.shadow(15.dp, CircleShape, spotColor = MagentaNeon)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(30.dp))
            }
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
                .padding(horizontal = 20.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
                    }
                    Text("Me deben y Debo", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
                Icon(Icons.Default.FilterList, contentDescription = null, tint = Color.White)
            }

            // Tarjeta Balance
            Card(
                colors = CardDefaults.cardColors(containerColor = CardDark),
                shape = RoundedCornerShape(28.dp),
                modifier = Modifier.fillMaxWidth().shadow(10.dp, RoundedCornerShape(28.dp), spotColor = PurpleNeon)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Balance total pendiente", color = Color.White.copy(0.6f), fontSize = 14.sp)
                    Spacer(Modifier.height(12.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text("Me deben", color = GreenNeon, fontSize = 13.sp)
                            Text("$600", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Black)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("Debo", color = RedNeon, fontSize = 13.sp)
                            Text("$150", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Black)
                        }
                    }
                    HorizontalDivider(Modifier.padding(vertical = 16.dp), color = Color.White.copy(0.1f))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Resultado neto:", color = Color.White, fontWeight = FontWeight.Medium)
                        Text("+$450", color = GreenNeon, fontSize = 18.sp, fontWeight = FontWeight.Black)
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // Selector de Tabs
            Row(
                modifier = Modifier.fillMaxWidth().height(56.dp).clip(RoundedCornerShape(18.dp)).background(CardDark).padding(5.dp)
            ) {
                val colorLeft by animateColorAsState(if (selectedTab == 0) MagentaNeon else Color.Transparent, label = "")
                val colorRight by animateColorAsState(if (selectedTab == 1) MagentaNeon else Color.Transparent, label = "")

                Box(Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(14.dp)).background(colorLeft).clickable { selectedTab = 0 }, contentAlignment = Alignment.Center) {
                    Text("🟢 Me deben", color = Color.White, fontWeight = if(selectedTab == 0) FontWeight.Bold else FontWeight.Normal)
                }
                Box(Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(14.dp)).background(colorRight).clickable { selectedTab = 1 }, contentAlignment = Alignment.Center) {
                    Text("🔴 Debo", color = Color.White, fontWeight = if(selectedTab == 1) FontWeight.Bold else FontWeight.Normal)
                }
            }

            Spacer(Modifier.height(20.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp), contentPadding = PaddingValues(bottom = 100.dp)) {
                val mockData = if (selectedTab == 0) listOf("Carlos", "Sofia", "Ximena") else listOf("Andres", "Tienda")
                items(mockData) { name ->
                    DebtItemCard(name, isMeDeben = selectedTab == 0)
                }
            }
        }
    }
}

@Composable
fun DebtItemCard(name: String, isMeDeben: Boolean) {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardDark),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(46.dp).clip(CircleShape).background(PurpleNeon.copy(0.15f)), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Person, null, tint = PurpleNeon)
                }
                Spacer(Modifier.width(14.dp))
                Column(Modifier.weight(1f)) {
                    Text(name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                    Text("Pendiente", color = YellowNeon, fontSize = 12.sp)
                }
                Text(
                    text = if(isMeDeben) "+$120" else "-$80", 
                    color = if(isMeDeben) GreenNeon else RedNeon, 
                    fontWeight = FontWeight.Black, 
                    fontSize = 18.sp
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { }, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Default.Edit, "Editar", tint = Color.White.copy(0.6f), modifier = Modifier.size(20.dp))
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { }, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Default.Delete, "Eliminar", tint = RedNeon.copy(0.8f), modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DebtPreview() {
    DebtScreen()
}
