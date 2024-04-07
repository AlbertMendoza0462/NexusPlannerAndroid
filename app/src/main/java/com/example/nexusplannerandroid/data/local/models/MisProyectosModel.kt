package com.example.nexusplannerandroid.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MisProyectos")
data class MisProyectosModel(
    @PrimaryKey(autoGenerate = true)
    val proyectoId: Int,
    val usuarioId: Int,
    val nombre: String,
    val descripcion: String,
    val fechaFinal: String,
    val fechaCreacion: String,
    val estado: Int,
    // val Usuario: Usuario?,
    // val Colaboradores: List<Usuario>?,
    // val Tareas: List<Tarea>?,
)
