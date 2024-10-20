package com.example.biblioteca_parcial.Screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.biblioteca_parcial.Model.Miembro
import com.example.biblioteca_parcial.Repository.MiembroRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MiembroScreen(navController: NavHostController, miembroRepository: MiembroRepository) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var fechaInscripcion by remember { mutableStateOf("") }
    var miembroId by remember { mutableStateOf("") }
    var idDelete by remember { mutableStateOf("") }
    var miembros by remember { mutableStateOf(listOf<Miembro>()) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    // Función para convertir la fecha
    fun parseDate(dateString: String): Date? {
        return try {
            dateFormat.parse(dateString)
        } catch (e: Exception) {
            Toast.makeText(context, "Formato de fecha inválido. Usa 'yyyy-MM-dd'", Toast.LENGTH_SHORT).show()
            null
        }
    }

    // Cargar los miembros al iniciar la pantalla
    LaunchedEffect(Unit) {
        scope.launch {
            miembros = withContext(Dispatchers.IO) {
                miembroRepository.getAllMiembros()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFF66BB6A), Color(0xFF43A047))
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registro de Miembros",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Campos para ingresar la información del miembro
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = apellido,
                onValueChange = { apellido = it },
                label = { Text("Apellido") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = fechaInscripcion,
                onValueChange = { fechaInscripcion = it },
                label = { Text("Fecha de Inscripción (yyyy-MM-dd)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            // Botón para registrar un nuevo miembro
            Button(
                onClick = {
                    val fechaInscripcionDate = parseDate(fechaInscripcion)
                    if (nombre.isNotBlank() && apellido.isNotBlank() && fechaInscripcionDate != null) {
                        val miembro = Miembro(
                            miembro_id = 0, // Pasa 0 si es autogenerado
                            nombre = nombre,
                            apellido = apellido,
                            fecha_inscripcion = fechaInscripcionDate
                        )
                        scope.launch {
                            try {
                                withContext(Dispatchers.IO) { miembroRepository.insert(miembro) }
                                miembros = withContext(Dispatchers.IO) { miembroRepository.getAllMiembros() }
                                Toast.makeText(context, "Miembro registrado", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error al registrar miembro", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF66BB6A), Color(0xFF43A047))
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Text("Registrar", color = Color.White)
            }

            // Botón para listar todos los miembros
            Button(
                onClick = {
                    scope.launch {
                        miembros = withContext(Dispatchers.IO) {
                            miembroRepository.getAllMiembros()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF66BB6A), Color(0xFF43A047))
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Text("Listar Miembros", color = Color.White)
            }

            // Listar los miembros
            Column {
                miembros.forEach { miembro ->
                    Text(
                        text = "ID: ${miembro.miembro_id}, Nombre: ${miembro.nombre}, Apellido: ${miembro.apellido}, Fecha Inscripción: ${miembro.fecha_inscripcion}",
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }

            // Eliminar un miembro por su ID
            OutlinedTextField(
                value = idDelete,
                onValueChange = { idDelete = it },
                label = { Text("ID del Miembro a Eliminar") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            // Modificación del botón "Eliminar" con borde rojo
            Button(
                onClick = {
                    val id = idDelete.toIntOrNull()
                    if (id != null) {
                        val miembro = Miembro(id, "", "", Date())
                        scope.launch {
                            try {
                                withContext(Dispatchers.IO) {
                                    miembroRepository.deleteMiembro(miembro)
                                }
                                miembros = withContext(Dispatchers.IO) {
                                    miembroRepository.getAllMiembros()
                                }
                                Toast.makeText(context, "Miembro eliminado", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error al eliminar el miembro", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "ID no válido", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .border(1.dp, Color.Red, RoundedCornerShape(8.dp)) // Borde rojo
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFFEF5350), Color(0xFFE53935))
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Text("Eliminar", color = Color.White)
            }

            // Actualizar un miembro por su ID
            OutlinedTextField(
                value = miembroId,
                onValueChange = { miembroId = it },
                label = { Text("ID del Miembro a Actualizar") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            Button(
                onClick = {
                    val id = miembroId.toIntOrNull()
                    val fechaInscripcionDate = parseDate(fechaInscripcion)
                    if (id != null && nombre.isNotBlank() && apellido.isNotBlank() && fechaInscripcionDate != null) {
                        val miembro = Miembro(
                            id,
                            nombre,
                            apellido,
                            fechaInscripcionDate
                        )
                        scope.launch {
                            try {
                                withContext(Dispatchers.IO) {
                                    miembroRepository.insert(miembro)
                                }
                                miembros = withContext(Dispatchers.IO) {
                                    miembroRepository.getAllMiembros()
                                }
                                Toast.makeText(context, "Miembro actualizado", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error al actualizar el miembro", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF66BB6A), Color(0xFF43A047))
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Text("Actualizar", color = Color.White)
            }
        }
    }
}


