package com.example.nexusplannerandroid.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class NuevoUsuarioModel(
    val usuarioId: Int,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val telefono: String?,
    //val FechaCreacion: String,
    val estado: Int,
    val clave: String,
    //val Proyectos: List<Proyecto>?,
)