package com.example.nexusplannerandroid.data.remote.api_dao

import com.example.nexusplannerandroid.data.local.models.LoginModel
import com.example.nexusplannerandroid.data.local.models.NuevoUsuarioModel
import com.example.nexusplannerandroid.data.remote.dto.UsuarioDto
import retrofit2.http.*

interface UsuarioApi {
    @POST("Usuarios/Login")
    suspend fun login(@Body login: LoginModel): UsuarioDto

    @POST("Usuarios")
    suspend fun insert(@Body usuario: NuevoUsuarioModel): Boolean
}