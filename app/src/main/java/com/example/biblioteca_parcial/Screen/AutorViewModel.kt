package com.example.biblioteca_parcial.Screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biblioteca_parcial.Model.Autor
import com.example.biblioteca_parcial.Repository.AutorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AutorViewModel(private val autorRepository: AutorRepository) : ViewModel() {

    private val _autores = MutableStateFlow<List<Autor>>(emptyList())
    val autores: StateFlow<List<Autor>> = _autores

    fun insertAutor(autor: Autor) {
        viewModelScope.launch {
            autorRepository.insert(autor)
            _autores.value = autorRepository.getAllAutores()
        }
    }

    fun updateAutor(autor: Autor) {
        viewModelScope.launch {
            autorRepository.insert(autor) // Asumiendo que `insert` es una operación upsert
            _autores.value = autorRepository.getAllAutores()
        }
    }

    fun deleteAutor(autor: Autor) {
        viewModelScope.launch {
            autorRepository.deleteAutor(autor)
            _autores.value = autorRepository.getAllAutores()
        }
    }

    fun loadAutores() {
        viewModelScope.launch {
            _autores.value = autorRepository.getAllAutores()
        }
    }
}