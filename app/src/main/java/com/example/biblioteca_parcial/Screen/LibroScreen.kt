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
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import com.example.biblioteca_parcial.Model.Libro
import com.example.biblioteca_parcial.Repository.LibroRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LibroScreen(navController: NavHostController, libroRepository: LibroRepository) {
    var titulo by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var autorId by remember { mutableStateOf("") }
    var libroId by remember { mutableStateOf("") }
    var idDelete by remember { mutableStateOf("") }
    var libros by remember { mutableStateOf(listOf<Libro>()) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // Cargar libros al iniciar la pantalla
    LaunchedEffect(Unit) {
        scope.launch {
            libros = withContext(Dispatchers.IO) {
                libroRepository.getAllLibros()
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
                text = "Registro de Libros",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Campos para ingresar información del libro
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = genero,
                onValueChange = { genero = it },
                label = { Text("Género") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = autorId,
                onValueChange = { autorId = it },
                label = { Text("ID del Autor") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            // Botón para registrar un nuevo libro
            Button(
                onClick = {
                    if (titulo.isNotBlank() && genero.isNotBlank() && autorId.isNotBlank()) {
                        val libro = Libro(
                            libro_id = 0, // Pasa 0 si es autogenerado
                            titulo = titulo,
                            genero = genero,
                            autor_id = autorId.toInt()  // Convertir autorId a Int
                        )
                        scope.launch {
                            try {
                                withContext(Dispatchers.IO) { libroRepository.insert(libro) }
                                libros = withContext(Dispatchers.IO) { libroRepository.getAllLibros() }
                                Toast.makeText(context, "Libro registrado", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error al registrar libro", Toast.LENGTH_SHORT).show()
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

            // Botón para listar todos los libros
            Button(
                onClick = {
                    scope.launch {
                        libros = withContext(Dispatchers.IO) { libroRepository.getAllLibros() }
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
                Text("Listar Libros", color = Color.White)
            }

            // Mostrar la lista de libros
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                libros.forEach { libro ->
                    Text(
                        text = "ID: ${libro.libro_id}, Título: ${libro.titulo}, Género: ${libro.genero}, Autor ID: ${libro.autor_id}",
                        color = Color.White,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Divider(color = Color.Gray, thickness = 1.dp)
                }
            }

            // Sección para eliminar un libro por su ID
            OutlinedTextField(
                value = idDelete,
                onValueChange = { idDelete = it },
                label = { Text("ID del Libro a Eliminar") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            Button(
                onClick = {
                    val id = idDelete.toIntOrNull()
                    if (id != null) {
                        val libro = Libro(id, "", "", 0)
                        scope.launch {
                            try {
                                withContext(Dispatchers.IO) {
                                    libroRepository.deleteLibro(libro)
                                }
                                libros = withContext(Dispatchers.IO) {
                                    libroRepository.getAllLibros()
                                }
                                Toast.makeText(context, "Libro eliminado", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error al eliminar el libro", Toast.LENGTH_SHORT).show()
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

            // Sección para actualizar un libro por su ID
            OutlinedTextField(
                value = libroId,
                onValueChange = { libroId = it },
                label = { Text("ID del Libro a Actualizar") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            Button(
                onClick = {
                    val id = libroId.toIntOrNull()
                    if (id != null && titulo.isNotBlank() && genero.isNotBlank() && autorId.isNotBlank()) {
                        val libro = Libro(id, titulo, genero, autorId.toInt())
                        scope.launch {
                            try {
                                withContext(Dispatchers.IO) {
                                    libroRepository.insert(libro)
                                }
                                libros = withContext(Dispatchers.IO) {
                                    libroRepository.getAllLibros()
                                }
                                Toast.makeText(context, "Libro actualizado", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error al actualizar el libro", Toast.LENGTH_SHORT).show()
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

