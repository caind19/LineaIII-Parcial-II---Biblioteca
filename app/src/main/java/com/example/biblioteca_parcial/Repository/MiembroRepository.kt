package com.example.biblioteca_parcial.Repository

import com.example.biblioteca_parcial.DAO.MiembroDao
import com.example.biblioteca_parcial.Model.Miembro

class MiembroRepository(private val miembroDao: MiembroDao) {
    suspend fun insert(miembro: Miembro) = miembroDao.insert(miembro)
    suspend fun getAllMiembros() = miembroDao.getAllMiembros()
    suspend fun getMiembroById(id: Int) = miembroDao.getMiembroById(id)
    suspend fun deleteMiembro(miembro: Miembro) = miembroDao.deleteMiembro(miembro)
}
