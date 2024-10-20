package com.example.biblioteca_parcial.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.biblioteca_parcial.DAO.LibroDao
import com.example.biblioteca_parcial.DAO.AutorDao
import com.example.biblioteca_parcial.DAO.MiembroDao
import com.example.biblioteca_parcial.DAO.PrestamoDao
import com.example.biblioteca_parcial.Model.Libro
import com.example.biblioteca_parcial.Model.Autor
import com.example.biblioteca_parcial.Model.Miembro
import com.example.biblioteca_parcial.Model.Prestamo
import androidx.room.TypeConverters
import com.example.biblioteca_parcial.util.Converters

@Database(entities = [Libro::class, Autor::class, Prestamo::class, Miembro::class], version = 1)
@TypeConverters(Converters::class) // Agrega esta línea para registrar los conversores
abstract class AppDatabase : RoomDatabase() {

    abstract fun libroDao(): LibroDao
    abstract fun autorDao(): AutorDao
    abstract fun prestamoDao(): PrestamoDao
    abstract fun miembroDao(): MiembroDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "biblioteca_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

