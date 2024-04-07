package com.example.nexusplannerandroid.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuarios")
data class UsuarioModel(
    @PrimaryKey
    val usuarioId: Int,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val telefono: String?,
    //val FechaCreacion: String,
    val estado: Int,
    //val Clave: String,
    //val Proyectos: List<Proyecto>?,

)