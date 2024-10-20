package com.example.biblioteca_parcial.DAO

import androidx.room.*
import com.example.biblioteca_parcial.Model.Autor

@Dao
interface AutorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(autor: Autor)

    @Query("SELECT * FROM autores")
    suspend fun getAllAutores(): List<Autor>

    @Query("SELECT * FROM autores WHERE autor_id = :id")
    suspend fun getAutorById(id: Int): Autor?

    @Delete
    suspend fun deleteAutor(autor: Autor)
}
