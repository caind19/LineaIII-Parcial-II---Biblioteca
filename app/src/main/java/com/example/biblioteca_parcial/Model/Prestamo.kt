package com.example.biblioteca_parcial.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.ForeignKey
import java.util.Date

@Entity(
    tableName = "prestamos",
    foreignKeys = [
        ForeignKey(
            entity = Libro::class,
            parentColumns = ["libro_id"], // Clave primaria en la tabla `libros`
            childColumns = ["libro_id"], // Clave foránea en la tabla `prestamos`
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Miembro::class,
            parentColumns = ["miembro_id"], // Clave primaria en la tabla `miembros`
            childColumns = ["miembro_id"], // Clave foránea en la tabla `prestamos`
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Prestamo(
    @PrimaryKey(autoGenerate = true) val prestamo_id: Int,
    @ColumnInfo(name = "libro_id") val libro_id: Int, // Clave foránea hacia `libros`
    @ColumnInfo(name = "miembro_id") val miembro_id: Int, // Clave foránea hacia `miembros`
    @ColumnInfo(name = "fecha_prestamo") val fecha_prestamo: Date?,
    @ColumnInfo(name = "fecha_devolucion") val fecha_devolucion: Date?
)
