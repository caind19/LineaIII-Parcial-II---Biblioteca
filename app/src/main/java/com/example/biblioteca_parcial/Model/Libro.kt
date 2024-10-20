package com.example.biblioteca_parcial.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.ForeignKey
import java.util.Date

@Entity(
    tableName = "libros",
    foreignKeys = [ForeignKey(
        entity = Autor::class,
        parentColumns = ["autor_id"], // Clave primaria en la tabla `autores`
        childColumns = ["autor_id"], // Clave foránea en la tabla `libros`
        onDelete = ForeignKey.CASCADE
    )]
)
data class Libro(
    @PrimaryKey(autoGenerate = true) val libro_id: Int,
    @ColumnInfo(name = "titulo") val titulo: String?,
    @ColumnInfo(name = "genero") val genero: String?,
    @ColumnInfo(name = "autor_id") val autor_id: Int? // Clave foránea hacia `autores`
)
