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
import com.example.biblioteca_parcial.Model.Autor
import com.example.biblioteca_parcial.Repository.AutorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AutorScreen(navController: NavHostController, autorRepository: AutorRepository) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var nacionalidad by remember { mutableStateOf("") }
    var autorId by remember { mutableStateOf("") }
    var idDelete by remember { mutableStateOf("") }
    var autores by remember { mutableStateOf(listOf<Autor>()) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // Cargar autores al iniciar la pantalla
    LaunchedEffect(Unit) {
        scope.launch {
            autores = withContext(Dispatchers.IO) {
                autorRepository.getAllAutores()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFF66BB6A), Color(0xFF43A047))  // Verde uniforme
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
                text = "Registro de Autores",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Campos para ingresar información del autor
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
                value = nacionalidad,
                onValueChange = { nacionalidad = it },
                label = { Text("Nacionalidad") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            // Botón para registrar un nuevo autor
            Button(
                onClick = {
                    if (nombre.isNotBlank() && apellido.isNotBlank() && nacionalidad.isNotBlank()) {
                        val autor = Autor(
                            autor_id = 0, // Pasa 0 si es autogenerado
                            nombre = nombre,
                            apellido = apellido,
                            nacionalidad = nacionalidad
                        )
                        scope.launch {
                            try {
                                withContext(Dispatchers.IO) { autorRepository.insert(autor) }
                                autores = withContext(Dispatchers.IO) { autorRepository.getAllAutores() }
                                Toast.makeText(context, "Autor registrado", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error al registrar autor", Toast.LENGTH_SHORT).show()
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
                            colors = listOf(Color(0xFF66BB6A), Color(0xFF43A047))  // Verde uniforme
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Text("Registrar", color = Color.White)
            }

            // Botón para listar todos los autores
            Button(
                onClick = {
                    scope.launch {
                        autores = withContext(Dispatchers.IO) { autorRepository.getAllAutores() }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF66BB6A), Color(0xFF43A047))  // Verde uniforme
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Text("Listar Autores", color = Color.White)
            }

            // Sección para eliminar un autor por su ID
            OutlinedTextField(
                value = idDelete,
                onValueChange = { idDelete = it },
                label = { Text("ID del Autor a Eliminar") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            Button(
                onClick = {
                    val id = idDelete.toIntOrNull()
                    if (id != null) {
                        val autor = Autor(id, "", "", "")
                        scope.launch {
                            try {
                                withContext(Dispatchers.IO) {
                                    autorRepository.deleteAutor(autor)
                                }
                                autores = withContext(Dispatchers.IO) {
                                    autorRepository.getAllAutores()
                                }
                                Toast.makeText(context, "Autor eliminado", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error al eliminar el autor", Toast.LENGTH_SHORT).show()
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
                            colors = listOf(Color(0xFFEF5350), Color(0xFFE53935))  // Rojo para eliminar
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Text("Eliminar", color = Color.White)
            }

            // Sección para actualizar un autor por su ID
            OutlinedTextField(
                value = autorId,
                onValueChange = { autorId = it },
                label = { Text("ID del Autor a Actualizar") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            Button(
                onClick = {
                    val id = autorId.toIntOrNull()
                    if (id != null && nombre.isNotBlank() && apellido.isNotBlank() && nacionalidad.isNotBlank()) {
                        val autor = Autor(id, nombre, apellido, nacionalidad)
                        scope.launch {
                            try {
                                withContext(Dispatchers.IO) {
                                    autorRepository.insert(autor) // Room maneja el upsert
                                }
                                autores = withContext(Dispatchers.IO) {
                                    autorRepository.getAllAutores()
                                }
                                Toast.makeText(context, "Autor actualizado", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error al actualizar el autor", Toast.LENGTH_SHORT).show()
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
                            colors = listOf(Color(0xFF66BB6A), Color(0xFF43A047))  // Verde para actualizar
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Text("Actualizar", color = Color.White)
            }

            // Listar los autores
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                autores.forEach { autor ->
                    Text(
                        text = "ID: ${autor.autor_id}, Nombre: ${autor.nombre}, Apellido: ${autor.apellido}, Nacionalidad: ${autor.nacionalidad}",
                        color = Color.White,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Divider(color = Color.Gray, thickness = 1.dp)
                }
            }
        }
    }
}

