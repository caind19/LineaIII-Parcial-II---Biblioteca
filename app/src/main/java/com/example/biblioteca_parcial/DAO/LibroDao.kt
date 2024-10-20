package com.example.biblioteca_parcial.DAO

import androidx.room.*
import com.example.biblioteca_parcial.Model.Libro
import com.example.biblioteca_parcial.Model.LibroConAutor

@Dao
interface LibroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(libro: Libro)

    @Query("SELECT * FROM libros")
    suspend fun getAllLibros(): List<Libro>

    @Query("SELECT * FROM libros WHERE libro_id = :id")
    suspend fun getLibroById(id: Int): Libro?

    @Delete
    suspend fun deleteLibro(libro: Libro)

    @Transaction  // Necesario para relaciones
    @Query("SELECT * FROM libros WHERE libro_id = :libroId")
    suspend fun getLibroConAutor(libroId: Int): LibroConAutor
}

