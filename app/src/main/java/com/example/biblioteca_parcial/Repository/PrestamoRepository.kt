package com.example.biblioteca_parcial.Repository

import com.example.biblioteca_parcial.DAO.PrestamoDao
import com.example.biblioteca_parcial.Model.Prestamo
import com.example.biblioteca_parcial.Model.PrestamoConLibroYMiembro

class PrestamoRepository(private val prestamoDao: PrestamoDao) {
    suspend fun insert(prestamo: Prestamo) = prestamoDao.insert(prestamo)
    suspend fun getAllPrestamos() = prestamoDao.getAllPrestamos()
    suspend fun getPrestamoById(id: Int) = prestamoDao.getPrestamoById(id)
    suspend fun deletePrestamo(prestamo: Prestamo) = prestamoDao.deletePrestamo(prestamo)

    // Nuevo método para obtener los préstamos junto con los libros y miembros
    suspend fun getPrestamosConLibrosYMiembros(): List<PrestamoConLibroYMiembro> {
        return prestamoDao.getPrestamosConLibrosYMiembros()
    }
}



