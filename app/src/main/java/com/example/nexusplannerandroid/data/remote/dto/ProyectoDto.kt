package com.example.nexusplannerandroid.data.remote.dto

import com.example.nexusplannerandroid.data.local.models.TareaModel
import com.example.nexusplannerandroid.data.local.models.UsuarioModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProyectoDto(
    val proyectoId: Int,
    val usuarioId: Int,
    val nombre: String,
    val descripcion: String,
    val fechaFinal: String,
    val fechaCreacion: String,
    val estado: Int,
    val usuario: UsuarioModel?,
    val colaboradores: List<UsuarioModel>?,
    val tareas: List<TareaModel>?,
)
