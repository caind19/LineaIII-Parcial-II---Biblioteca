package com.example.biblioteca_parcial.Model

import androidx.room.Embedded
import androidx.room.Relation

data class PrestamoConLibroYMiembro(
    @Embedded val prestamo: Prestamo,  // Tabla de préstamos
    @Relation(
        parentColumn = "libro_id",  // Clave foránea en Prestamo
        entityColumn = "libro_id"   // Clave primaria en Libro
    )
    val libro: Libro,  // Entidad Libro
    @Relation(
        parentColumn = "miembro_id",  // Clave foránea en Prestamo
        entityColumn = "miembro_id"   // Clave primaria en Miembro
    )
    val miembro: Miembro  // Entidad Miembro
)
