package com.example.biblioteca_parcial.Screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biblioteca_parcial.Model.Prestamo
import com.example.biblioteca_parcial.Repository.PrestamoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PrestamoViewModel(private val prestamoRepository: PrestamoRepository) : ViewModel() {

    private val _prestamos = MutableStateFlow<List<Prestamo>>(emptyList())
    val prestamos: StateFlow<List<Prestamo>> = _prestamos

    fun insertPrestamo(prestamo: Prestamo) {
        viewModelScope.launch {
            prestamoRepository.insert(prestamo)
            _prestamos.value = prestamoRepository.getAllPrestamos()
        }
    }

    fun updatePrestamo(prestamo: Prestamo) {
        viewModelScope.launch {
            prestamoRepository.insert(prestamo) // Asumiendo que `insert` es una operación upsert
            _prestamos.value = prestamoRepository.getAllPrestamos()
        }
    }

    fun deletePrestamo(prestamo: Prestamo) {
        viewModelScope.launch {
            prestamoRepository.deletePrestamo(prestamo)
            _prestamos.value = prestamoRepository.getAllPrestamos()
        }
    }

    fun loadPrestamos() {
        viewModelScope.launch {
            _prestamos.value = prestamoRepository.getAllPrestamos()
        }
    }
}