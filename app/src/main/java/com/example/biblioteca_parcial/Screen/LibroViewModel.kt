package com.example.biblioteca_parcial.Screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biblioteca_parcial.Model.Libro
import com.example.biblioteca_parcial.Repository.LibroRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LibroViewModel(private val libroRepository: LibroRepository) : ViewModel() {

    private val _libros = MutableStateFlow<List<Libro>>(emptyList())
    val libros: StateFlow<List<Libro>> = _libros

    fun insertLibro(libro: Libro) {
        viewModelScope.launch {
            libroRepository.insert(libro)
            _libros.value = libroRepository.getAllLibros()
        }
    }

    fun updateLibro(libro: Libro) {
        viewModelScope.launch {
            libroRepository.insert(libro) // Asumiendo que `insert` es una operación upsert
            _libros.value = libroRepository.getAllLibros()
        }
    }

    fun deleteLibro(libro: Libro) {
        viewModelScope.launch {
            libroRepository.deleteLibro(libro)
            _libros.value = libroRepository.getAllLibros()
        }
    }

    fun loadLibros() {
        viewModelScope.launch {
            _libros.value = libroRepository.getAllLibros()
        }
    }
}
