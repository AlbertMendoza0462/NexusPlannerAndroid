package com.example.nexusplannerandroid.data.local.repository

import com.example.nexusplannerandroid.data.local.AppDataBase
import com.example.nexusplannerandroid.data.local.models.NuevoUsuarioModel
import com.example.nexusplannerandroid.data.local.models.UsuarioModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsuarioRepository @Inject constructor(
    private val db: AppDataBase
) {
    suspend fun insertUsuario(usuario: UsuarioModel){
        db.usuarioDao.insertUsuario(usuario)
    }

    suspend fun getFirstUsuario(): Flow<UsuarioModel?> {
        return db.usuarioDao.getFirstUsuario()
    }

    suspend fun truncateTableUsuario() {
        return db.usuarioDao.truncateTableUsuarios()
    }
}