package com.example.nexusplannerandroid.data.remote.api_repository

import com.example.nexusplannerandroid.data.local.models.LoginModel
import com.example.nexusplannerandroid.data.local.models.NuevoUsuarioModel
import com.example.nexusplannerandroid.data.remote.api_dao.UsuarioApi
import com.example.nexusplannerandroid.data.remote.dto.UsuarioDto
import javax.inject.Inject

class UsuarioApiRepository @Inject constructor(
    private val api: UsuarioApi
) {
    suspend fun insertUsuario(usuario: NuevoUsuarioModel): Boolean {
        try {
            return this.api.insert(usuario)
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

    suspend fun login(login: LoginModel): UsuarioDto? {
        try {
            return this.api.login(login)
        } catch (e: Exception) {
            println(e)
            return null
        }
    }
}