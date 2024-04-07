package com.example.nexusplannerandroid.data.remote.api_repository

import com.example.nexusplannerandroid.data.remote.api_dao.TareaApi
import com.example.nexusplannerandroid.data.remote.dto.TareaDto
import javax.inject.Inject

class TareaApiRepository @Inject constructor(
    private val api: TareaApi
) {
    suspend fun getTareas(id: String): List<TareaDto>{
        try {
            val api = api.getTareasPorUsuario(id)
            return api
        }catch (e: Exception){
            println(e)
            return emptyList()
        }
    }

    suspend fun terminarTarea(id: String): Boolean {
        try {
            return this.api.terminarTarea(id)
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

    suspend fun iniciarTarea(id: String): Boolean {
        try {
            return this.api.iniciarTarea(id)
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

    suspend fun pausarTarea(id: String): Boolean {
        try {
            return this.api.pausarTarea(id)
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

    suspend fun reanudarTarea(id: String): Boolean {
        try {
            return this.api.reanudarTarea(id)
        } catch (e: Exception) {
            println(e)
            return false
        }
    }
}