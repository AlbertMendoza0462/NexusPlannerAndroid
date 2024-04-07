package com.example.nexusplannerandroid.data.remote.api_dao

import com.example.nexusplannerandroid.data.remote.dto.TareaDto
import retrofit2.http.*

interface TareaApi {

    @GET("Tareas/PorUsuario/{id}")
    suspend fun getTareasPorUsuario(@Path("id") id: String): List<TareaDto>

    @POST("Tareas/Terminar/{id}")
    suspend fun terminarTarea(@Path("id") id: String): Boolean

    @POST("Tareas/Iniciar/{id}")
    suspend fun iniciarTarea(@Path("id") id: String): Boolean

    @POST("Tareas/Pausar/{id}")
    suspend fun pausarTarea(@Path("id") id: String): Boolean

    @POST("Tareas/Reanudar/{id}")
    suspend fun reanudarTarea(@Path("id") id: String): Boolean

}