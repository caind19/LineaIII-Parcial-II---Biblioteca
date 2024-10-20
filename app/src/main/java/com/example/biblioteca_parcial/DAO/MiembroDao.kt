package com.example.biblioteca_parcial.DAO

import androidx.room.*
import com.example.biblioteca_parcial.Model.Miembro

@Dao
interface MiembroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(miembro: Miembro)

    @Query("SELECT * FROM miembros")
    suspend fun getAllMiembros(): List<Miembro>

    @Query("SELECT * FROM miembros WHERE miembro_id = :id")
    suspend fun getMiembroById(id: Int): Miembro?

    @Delete
    suspend fun deleteMiembro(miembro: Miembro)
}
