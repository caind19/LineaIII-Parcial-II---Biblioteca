package com.example.biblioteca_parcial.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.util.Date

@Entity(tableName = "miembros")
data class Miembro(
    @PrimaryKey(autoGenerate = true) val miembro_id: Int,
    @ColumnInfo(name = "nombre") val nombre: String?,
    @ColumnInfo(name = "apellido") val apellido: String?,
    @ColumnInfo(name = "fecha_inscripcion") val fecha_inscripcion: Date?
)