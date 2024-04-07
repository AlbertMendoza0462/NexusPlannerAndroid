package com.example.nexusplannerandroid.data.remote.api_dao

import com.example.nexusplannerandroid.data.remote.dto.ProyectoDto
import retrofit2.http.*

interface ProyectoApi {
    @GET("Proyectos/MisProyectos/{id}")
    suspend fun getMisProyectos(@Path("id") id: String): List<ProyectoDto>

    @GET("Proyectos/OtrosProyectos/{id}")
    suspend fun getOtrosProyectos(@Path("id") id: String): List<ProyectoDto>

    @POST("Proyectos/Terminar/{id}")
    suspend fun terminarProyecto(@Path("id") id: String): Boolean

    @POST("Proyectos/Reanudar/{id}")
    suspend fun reanudarProyecto(@Path("id") id: String): Boolean
}