package com.example.biblioteca_parcial.Screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.biblioteca_parcial.Repository.LibroRepository
import com.example.biblioteca_parcial.Repository.AutorRepository
import com.example.biblioteca_parcial.Repository.PrestamoRepository
import com.example.biblioteca_parcial.Repository.MiembroRepository

@Composable
fun Navegacion(
    libroRepository: LibroRepository,
    autorRepository: AutorRepository,
    prestamoRepository: PrestamoRepository,
    miembroRepository: MiembroRepository
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        // Pantalla de inicio
        composable("home") { HomeScreen(navController) }

        // Pantallas para cada entidad
        composable("libros") { LibroScreen(navController, libroRepository) }
        composable("autores") { AutorScreen(navController, autorRepository) }
        composable("prestamos") { PrestamoScreen(navController, prestamoRepository) }
        composable("miembros") { MiembroScreen(navController, miembroRepository) }
    }
}
