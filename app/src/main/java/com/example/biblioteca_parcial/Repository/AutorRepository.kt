package com.example.biblioteca_parcial.Repository

import com.example.biblioteca_parcial.DAO.AutorDao
import com.example.biblioteca_parcial.Model.Autor

class AutorRepository(private val autorDao: AutorDao) {
    suspend fun insert(autor: Autor) = autorDao.insert(autor)
    suspend fun getAllAutores() = autorDao.getAllAutores()
    suspend fun getAutorById(id: Int) = autorDao.getAutorById(id)
    suspend fun deleteAutor(autor: Autor) = autorDao.deleteAutor(autor)
}
