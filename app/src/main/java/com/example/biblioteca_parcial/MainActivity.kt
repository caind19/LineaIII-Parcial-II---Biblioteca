/*
    Aplicativo de Administración de Tablas Relacionadas con Base de Datos
    para la gestión de una bibliteca.
    Cain David Martinez Cardona
    901N
*/
package com.example.biblioteca_parcial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.biblioteca_parcial.DAO.AutorDao
import com.example.biblioteca_parcial.DAO.LibroDao
import com.example.biblioteca_parcial.DAO.MiembroDao
import com.example.biblioteca_parcial.DAO.PrestamoDao
import com.example.biblioteca_parcial.Database.AppDatabase
import com.example.biblioteca_parcial.Repository.AutorRepository
import com.example.biblioteca_parcial.Repository.LibroRepository
import com.example.biblioteca_parcial.Repository.MiembroRepository
import com.example.biblioteca_parcial.Repository.PrestamoRepository
import com.example.biblioteca_parcial.Screen.Navegacion
import com.example.biblioteca_parcial.ui.theme.BibliotecaParcialTheme

class MainActivity : ComponentActivity() {
    // Declaración de DAOs
    private lateinit var libroDao: LibroDao
    private lateinit var autorDao: AutorDao
    private lateinit var prestamoDao: PrestamoDao
    private lateinit var miembroDao: MiembroDao

    // Declaración de repositorios
    private lateinit var libroRepository: LibroRepository
    private lateinit var autorRepository: AutorRepository
    private lateinit var prestamoRepository: PrestamoRepository
    private lateinit var miembroRepository: MiembroRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicialización de la base de datos
        val db = AppDatabase.getDatabase(applicationContext)

        // Inicialización de DAOs
        libroDao = db.libroDao()
        autorDao = db.autorDao()
        prestamoDao = db.prestamoDao()
        miembroDao = db.miembroDao()

        // Inicialización de repositorios
        libroRepository = LibroRepository(libroDao)
        autorRepository = AutorRepository(autorDao)
        prestamoRepository = PrestamoRepository(prestamoDao)
        miembroRepository = MiembroRepository(miembroDao)

        // Activar modo edge-to-edge (inmersión en la pantalla)
        enableEdgeToEdge()

        // Configuración del contenido de la pantalla
        setContent {
            Navegacion(
                libroRepository,
                autorRepository,
                prestamoRepository,
                miembroRepository
            )
        }
    }
}