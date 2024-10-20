package com.example.biblioteca_parcial.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.util.Date

@Entity(tableName = "autores")
data class Autor(
    @PrimaryKey(autoGenerate = true) val autor_id: Int,
    @ColumnInfo(name = "nombre") val nombre: String?,
    @ColumnInfo(name = "apellido") val apellido: String?,
    @ColumnInfo(name = "nacionalidad") val nacionalidad: String?
)
