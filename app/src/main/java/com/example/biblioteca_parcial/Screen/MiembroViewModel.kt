package com.example.biblioteca_parcial.Screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biblioteca_parcial.Model.Miembro
import com.example.biblioteca_parcial.Repository.MiembroRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MiembroViewModel(private val miembroRepository: MiembroRepository) : ViewModel() {

    private val _miembros = MutableStateFlow<List<Miembro>>(emptyList())
    val miembros: StateFlow<List<Miembro>> = _miembros

    fun insertMiembro(miembro: Miembro) {
        viewModelScope.launch {
            miembroRepository.insert(miembro)
            _miembros.value = miembroRepository.getAllMiembros()
        }
    }

    fun updateMiembro(miembro: Miembro) {
        viewModelScope.launch {
            miembroRepository.insert(miembro) // Asumiendo que `insert` es una operación upsert
            _miembros.value = miembroRepository.getAllMiembros()
        }
    }

    fun deleteMiembro(miembro: Miembro) {
        viewModelScope.launch {
            miembroRepository.deleteMiembro(miembro)
            _miembros.value = miembroRepository.getAllMiembros()
        }
    }

    fun loadMiembros() {
        viewModelScope.launch {
            _miembros.value = miembroRepository.getAllMiembros()
        }
    }
}