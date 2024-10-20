package com.example.biblioteca_parcial.Repository

import com.example.biblioteca_parcial.DAO.LibroDao
import com.example.biblioteca_parcial.Model.Libro

class LibroRepository(private val libroDao: LibroDao) {
    suspend fun insert(libro: Libro) = libroDao.insert(libro)
    suspend fun getAllLibros() = libroDao.getAllLibros()
    suspend fun getLibroById(id: Int) = libroDao.getLibroById(id)
    suspend fun deleteLibro(libro: Libro) = libroDao.deleteLibro(libro)
}
