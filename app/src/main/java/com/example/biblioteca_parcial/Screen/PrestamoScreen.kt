package com.example.biblioteca_parcial.Screen

import android.widget.Toast
import androidx.compose.foundation.background
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
import com.example.biblioteca_parcial.Model.Prestamo
import com.example.biblioteca_parcial.Model.PrestamoConLibroYMiembro
import com.example.biblioteca_parcial.Repository.PrestamoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PrestamoScreen(navController: NavHostController, prestamoRepository: PrestamoRepository) {
    var libroId by remember { mutableStateOf("") }
    var miembroId by remember { mutableStateOf("") }
    var fechaPrestamo by remember { mutableStateOf("") }
    var fechaDevolucion by remember { mutableStateOf("") }
    var prestamoId by remember { mutableStateOf("") }
    var idDelete by remember { mutableStateOf("") }
    var prestamosConLibrosYMiembros by remember { mutableStateOf(listOf<PrestamoConLibroYMiembro>()) }
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

    // Cargar préstamos con sus libros y miembros al iniciar la pantalla
    LaunchedEffect(Unit) {
        scope.launch {
            prestamosConLibrosYMiembros = withContext(Dispatchers.IO) {
                prestamoRepository.getPrestamosConLibrosYMiembros()
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
                text = "Registro de Préstamos",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Campos para ingresar la información del préstamo
            OutlinedTextField(
                value = libroId,
                onValueChange = { libroId = it },
                label = { Text("ID del Libro") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = miembroId,
                onValueChange = { miembroId = it },
                label = { Text("ID del Miembro") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = fechaPrestamo,
                onValueChange = { fechaPrestamo = it },
                label = { Text("Fecha de Préstamo (yyyy-MM-dd)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = fechaDevolucion,
                onValueChange = { fechaDevolucion = it },
                label = { Text("Fecha de Devolución (yyyy-MM-dd)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            // Botón para registrar un nuevo préstamo
            Button(
                onClick = {
                    val fechaPrestamoDate = parseDate(fechaPrestamo)
                    val fechaDevolucionDate = parseDate(fechaDevolucion)
                    if (libroId.isNotBlank() && miembroId.isNotBlank() && fechaPrestamoDate != null) {
                        val prestamo = Prestamo(
                            prestamo_id = 0, // Pasa 0 si es autogenerado
                            libro_id = libroId.toInt(),
                            miembro_id = miembroId.toInt(),
                            fecha_prestamo = fechaPrestamoDate,
                            fecha_devolucion = fechaDevolucionDate
                        )
                        scope.launch {
                            try {
                                withContext(Dispatchers.IO) { prestamoRepository.insert(prestamo) }
                                prestamosConLibrosYMiembros = withContext(Dispatchers.IO) {
                                    prestamoRepository.getPrestamosConLibrosYMiembros()
                                }
                                Toast.makeText(context, "Préstamo registrado", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error al registrar préstamo", Toast.LENGTH_SHORT).show()
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

            // Botón para listar todos los préstamos
            Button(
                onClick = {
                    scope.launch {
                        prestamosConLibrosYMiembros = withContext(Dispatchers.IO) {
                            prestamoRepository.getPrestamosConLibrosYMiembros()
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
                Text("Listar Préstamos", color = Color.White)
            }

            // Listar préstamos con sus libros y miembros relacionados
            Column {
                prestamosConLibrosYMiembros.forEach { prestamoConLibroYMiembro ->
                    Text(
                        text = "Préstamo ID: ${prestamoConLibroYMiembro.prestamo.prestamo_id}, " +
                                "Libro: ${prestamoConLibroYMiembro.libro.titulo}, " +
                                "Miembro: ${prestamoConLibroYMiembro.miembro.nombre} ${prestamoConLibroYMiembro.miembro.apellido}",
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }

            // Eliminar un préstamo por su ID
            OutlinedTextField(
                value = idDelete,
                onValueChange = { idDelete = it },
                label = { Text("ID del Préstamo a Eliminar") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            Button(
                onClick = {
                    val id = idDelete.toIntOrNull()
                    if (id != null) {
                        val prestamo = Prestamo(id, 0, 0, Date(), null)
                        scope.launch {
                            try {
                                withContext(Dispatchers.IO) {
                                    prestamoRepository.deletePrestamo(prestamo)
                                }
                                prestamosConLibrosYMiembros = withContext(Dispatchers.IO) {
                                    prestamoRepository.getPrestamosConLibrosYMiembros()
                                }
                                Toast.makeText(context, "Préstamo eliminado", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error al eliminar el préstamo", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "ID no válido", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFFEF5350), Color(0xFFE53935))
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Text("Eliminar", color = Color.White)
            }

            // Actualizar un préstamo por su ID
            OutlinedTextField(
                value = prestamoId,
                onValueChange = { prestamoId = it },
                label = { Text("ID del Préstamo a Actualizar") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            Button(
                onClick = {
                    val id = prestamoId.toIntOrNull()
                    val fechaPrestamoDate = parseDate(fechaPrestamo)
                    val fechaDevolucionDate = parseDate(fechaDevolucion)

                    if (id != null && libroId.isNotBlank() && miembroId.isNotBlank() && fechaPrestamoDate != null) {
                        val prestamo = Prestamo(
                            id,
                            libroId.toInt(),
                            miembroId.toInt(),
                            fechaPrestamoDate,
                            fechaDevolucionDate
                        )
                        scope.launch {
                            try {
                                withContext(Dispatchers.IO) {
                                    prestamoRepository.insert(prestamo)
                                }
                                prestamosConLibrosYMiembros = withContext(Dispatchers.IO) {
                                    prestamoRepository.getPrestamosConLibrosYMiembros()
                                }
                                Toast.makeText(context, "Préstamo actualizado", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error al actualizar el préstamo", Toast.LENGTH_SHORT).show()
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

            // Listar préstamos con detalles de libro y miembro
            Column {
                prestamosConLibrosYMiembros.forEach { prestamoConLibroYMiembro ->
                    Text(
                        text = "ID: ${prestamoConLibroYMiembro.prestamo.prestamo_id}, " +
                                "Libro ID: ${prestamoConLibroYMiembro.libro.libro_id}, " +
                                "Miembro ID: ${prestamoConLibroYMiembro.miembro.miembro_id}, " +
                                "Fecha Préstamo: ${prestamoConLibroYMiembro.prestamo.fecha_prestamo}, " +
                                "Fecha Devolución: ${prestamoConLibroYMiembro.prestamo.fecha_devolucion ?: "No devuelto"}",
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

