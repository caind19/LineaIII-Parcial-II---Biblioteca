package com.example.biblioteca_parcial.DAO

import androidx.room.*
import com.example.biblioteca_parcial.Model.Prestamo
import com.example.biblioteca_parcial.Model.PrestamoConLibroYMiembro

@Dao
interface PrestamoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(prestamo: Prestamo)

    @Query("SELECT * FROM prestamos")
    suspend fun getAllPrestamos(): List<Prestamo>

    @Query("SELECT * FROM prestamos WHERE prestamo_id = :id")
    suspend fun getPrestamoById(id: Int): Prestamo?

    @Delete
    suspend fun deletePrestamo(prestamo: Prestamo)

    // Nuevo método para obtener las relaciones de préstamos con libros y miembros
    @Transaction
    @Query("SELECT * FROM prestamos")
    suspend fun getPrestamosConLibrosYMiembros(): List<PrestamoConLibroYMiembro>
}
