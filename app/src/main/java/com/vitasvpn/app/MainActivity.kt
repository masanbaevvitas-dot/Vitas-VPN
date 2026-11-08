package com.vitasvpn.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Night = Color(0xFF08111F)
private val Panel = Color(0xFF132238)
private val Mint = Color(0xFF49E0B2)
private val Muted = Color(0xFF9BAEC4)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { VitasVpnApp() }
    }
}

@Composable
private fun VitasVpnApp() {
    var selectedCountry by remember { mutableStateOf("Germany") }
    var showCountries by remember { mutableStateOf(false) }
    var killSwitch by remember { mutableStateOf(true) }

    MaterialTheme(colorScheme = darkColorScheme(
        background = Night, surface = Panel, primary = Mint,
        onBackground = Color.White, onSurface = Color.White
    )) {
        Box(
            modifier = Modifier.fillMaxSize().background(
                Brush.verticalGradient(listOf(Color(0xFF10253A), Night, Color(0xFF050A12)))
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("VITAS", color = Color.White, fontSize = 29.sp, fontWeight = FontWeight.Black, letterSpacing = 5.sp)
                Text("VPN", color = Mint, fontSize = 13.sp, fontWeight = FontWeight.Bold, letterSpacing = 7.sp)
                Spacer(Modifier.height(48.dp))
                Text("ЗАЩИТА СОЕДИНЕНИЯ", color = Muted, fontSize = 11.sp, letterSpacing = 2.sp)
                Spacer(Modifier.height(24.dp))
                Box(
                    modifier = Modifier.size(190.dp).background(Panel, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Box(Modifier.size(158.dp).background(Color(0xFF1B3549), CircleShape), contentAlignment = Alignment.Center) {
                        Text("V", color = Mint, fontSize = 76.sp, fontWeight = FontWeight.Light)
                    }
                }
                Spacer(Modifier.height(22.dp))
                Text("Не подключено", color = Color.White, fontSize = 23.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(8.dp))
                Text("Выбери сервер для начала работы", color = Muted, fontSize = 13.sp)
                Spacer(Modifier.height(32.dp))
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { showCountries = true },
                    colors = CardDefaults.cardColors(containerColor = Panel),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Row(Modifier.fillMaxWidth().padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text("🌐", fontSize = 25.sp)
                        Spacer(Modifier.width(14.dp))
                        Column(Modifier.weight(1f)) {
                            Text("Локация", color = Muted, fontSize = 12.sp)
                            Text(selectedCountry, color = Color.White, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
                        }
                        Text("Выбрать  ›", color = Mint, fontSize = 13.sp)
                    }
                }
                Spacer(Modifier.height(16.dp))
                Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(18.dp)) {
                    Row(Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text("Kill Switch", color = Color.White, fontWeight = FontWeight.SemiBold)
                            Text("Блокировка трафика при обрыве VPN", color = Muted, fontSize = 11.sp)
                        }
                        Switch(checked = killSwitch, onCheckedChange = { killSwitch = it })
                    }
                }
                Spacer(Modifier.weight(1f))
                Text("БЕСПЛАТНЫЙ ПРОЕКТ • V 0.1.0", color = Color(0xFF6F849B), fontSize = 10.sp, letterSpacing = 1.sp)
                Spacer(Modifier.height(10.dp))
                Text("Это тестовый интерфейс. VPN-туннель ещё не подключён.", color = Color(0xFFFFC66D), fontSize = 11.sp)
            }
        }
        if (showCountries) {
            AlertDialog(
                onDismissRequest = { showCountries = false },
                title = { Text("Выбери страну") },
                text = {
                    Column {
                        listOf("Germany", "Netherlands", "Poland", "Finland", "United States").forEach { country ->
                            TextButton(onClick = { selectedCountry = country; showCountries = false }) {
                                Text(country, color = Color.White)
                            }
                        }
                    }
                },
                confirmButton = { TextButton(onClick = { showCountries = false }) { Text("Закрыть") } },
                containerColor = Panel
            )
        }
    }
}
