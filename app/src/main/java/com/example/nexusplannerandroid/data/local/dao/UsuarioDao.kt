package com.example.nexusplannerandroid.data.local.dao

import androidx.room.*
import com.example.nexusplannerandroid.data.local.models.UsuarioModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsuario(usuario: UsuarioModel)

    @Query("Select * from Usuarios limit 1")
    fun getFirstUsuario(): Flow<UsuarioModel?>

    @Query("Delete from usuarios")
    fun truncateTableUsuarios()
}