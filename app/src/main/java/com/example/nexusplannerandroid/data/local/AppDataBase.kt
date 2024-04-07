package com.example.nexusplannerandroid.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nexusplannerandroid.data.local.dao.*
import com.example.nexusplannerandroid.data.local.models.*

@Database(
    entities = [
        EntornoModel::class,
        MisProyectosModel::class,
        OtrosProyectosModel::class,
        TareaModel::class,
        UsuarioModel::class
    ],
    version = 1,
    exportSchema = false
)

abstract class AppDataBase : RoomDatabase() {
    abstract val entornoDao: EntornoDao
    abstract val usuarioDao: UsuarioDao
    abstract val proyectoDao: ProyectoDao
    abstract val tareaDao: TareaDao
}