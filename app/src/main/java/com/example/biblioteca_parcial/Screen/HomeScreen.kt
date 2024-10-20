package com.example.biblioteca_parcial.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.shadow
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFB2DFDB), // Verde agua
            Color(0xFF80CBC4),
            Color(0xFF4DB6AC)  // Verde profundo
        )
    )

    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFF00796B), // Verde oscuro
        contentColor = Color.White
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Se agrega tanto scroll vertical como horizontal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Scroll vertical
                .horizontalScroll(rememberScrollState()) // Scroll horizontal
                .padding(16.dp)
                .border(2.dp, Color(0xFF004D40), RoundedCornerShape(16.dp)) // Borde y esquinas redondeadas
                .shadow(8.dp, RoundedCornerShape(16.dp)) // Sombra suave
                .background(Color.White.copy(alpha = 0.8f)) // Fondo semitransparente
                .padding(32.dp), // Espaciado interno
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "BIBLIOTECA ZZZ",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF004D40), // Verde oscuro
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Botones de navegación estilizados
            Button(
                onClick = { navController.navigate("libros") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color(0xFF00796B), RoundedCornerShape(8.dp)), // Borde del botón
                colors = buttonColors,
                shape = RoundedCornerShape(12.dp) // Esquinas redondeadas
            ) {
                Text("Registro de Libros", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("autores") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color(0xFF00796B), RoundedCornerShape(8.dp)), // Borde del botón
                colors = buttonColors,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Registro de Autores", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("prestamos") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color(0xFF00796B), RoundedCornerShape(8.dp)), // Borde del botón
                colors = buttonColors,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Registro de Préstamos", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("miembros") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color(0xFF00796B), RoundedCornerShape(8.dp)), // Borde del botón
                colors = buttonColors,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Registro de Miembros", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Texto de pie
            ClickableText(
                text = buildAnnotatedString {
                    append("¿Necesitas ayuda? ")
                    pushStyle(SpanStyle(color = Color(0xFF00796B), fontWeight = FontWeight.Bold))
                    append("Haz clic aquí")
                },
                style = TextStyle(fontSize = 14.sp),
                onClick = { /* Acción para la ayuda */ }
            )
        }
    }
}

