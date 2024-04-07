package com.example.nexusplannerandroid.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tareas")
data class TareaModel(
    @PrimaryKey(autoGenerate = true)
    val tareaId: Int,
    val proyectoId: Int,
    val usuarioId: Int,
    val nombre: String,
    val descripcion: String?,
    val fechaFinal: String,
    val fechaCreacion: String,
    val estado: Int,
    // val Usuario: Usuario?,
    // val Proyecto: Proyecto?,
)
