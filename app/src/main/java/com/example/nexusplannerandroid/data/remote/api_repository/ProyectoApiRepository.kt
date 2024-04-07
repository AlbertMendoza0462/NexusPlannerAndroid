package com.example.nexusplannerandroid.data.remote.api_repository

import com.example.nexusplannerandroid.data.remote.api_dao.ProyectoApi
import com.example.nexusplannerandroid.data.remote.dto.ProyectoDto
import javax.inject.Inject

class ProyectoApiRepository @Inject constructor(
    private val api: ProyectoApi
) {
    suspend fun getMisProyectos(id: String): List<ProyectoDto>{
        try {
            val api = api.getMisProyectos(id);
            return api
        }catch (e: Exception){
            e.printStackTrace()
            return emptyList()
        }
    }

    suspend fun getOtrosProyectos(id: String): List<ProyectoDto>{
        try {
            val api = api.getOtrosProyectos(id);
            return api
        }catch (e: Exception){
            println(e)
            return emptyList()
        }
    }

    suspend fun terminarProyecto(id: String): Boolean {
        try {
            return this.api.terminarProyecto(id)
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

    suspend fun reanudarProyecto(id: String): Boolean {
        try {
            return this.api.reanudarProyecto(id)
        } catch (e: Exception) {
            println(e)
            return false
        }
    }
}