package com.example.biblioteca_parcial.Model

import androidx.room.Embedded
import androidx.room.Relation

data class LibroConAutor(
    @Embedded val libro: Libro,  // Representa la tabla Libro
    @Relation(
        parentColumn = "autor_id",  // Clave foránea en Libro
        entityColumn = "autor_id"   // Clave primaria en Autor
    )
    val autor: Autor  // La entidad relacionada
)
