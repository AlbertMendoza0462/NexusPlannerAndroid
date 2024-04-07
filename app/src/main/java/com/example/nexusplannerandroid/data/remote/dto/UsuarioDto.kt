package com.example.nexusplannerandroid.data.remote.dto

import com.example.nexusplannerandroid.data.local.models.MisProyectosModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsuarioDto(
    val usuarioId: Int,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val telefono: String?,
    val estado: Int,
    val proyectos: List<MisProyectosModel>?,
)
