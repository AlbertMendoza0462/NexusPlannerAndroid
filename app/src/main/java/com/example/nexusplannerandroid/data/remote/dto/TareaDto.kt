package com.example.nexusplannerandroid.data.remote.dto

import com.example.nexusplannerandroid.data.local.models.MisProyectosModel
import com.example.nexusplannerandroid.data.local.models.UsuarioModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TareaDto(
    val tareaId: Int,
    val proyectoId: Int,
    val usuarioId: Int,
    val nombre: String,
    val descripcion: String?,
    val fechaFinal: String,
    val fechaCreacion: String,
    val estado: Int,
    val usuario: UsuarioModel?,
    val proyecto: MisProyectosModel?
)
