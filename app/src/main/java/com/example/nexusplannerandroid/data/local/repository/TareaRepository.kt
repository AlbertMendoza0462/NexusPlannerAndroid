package com.example.nexusplannerandroid.data.local.repository

import com.example.nexusplannerandroid.data.local.AppDataBase
import com.example.nexusplannerandroid.data.local.models.TareaModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TareaRepository @Inject constructor(
    private val db: AppDataBase
) {
    suspend fun insertTarea(tarea: TareaModel){
        db.tareaDao.insertTarea(tarea)
    }

    suspend fun deleteTarea(tarea: TareaModel){
        db.tareaDao.deleteTarea(tarea)
    }

    suspend fun getTareas(): Flow<List<TareaModel>> {
        return db.tareaDao.getTareas()
    }

    suspend fun getTareaById(id: Int): Flow<TareaModel?> {
        return db.tareaDao.getTareaById(id)
    }

    suspend fun truncateTableTarea() {
        return db.tareaDao.truncateTableTareas()
    }
}