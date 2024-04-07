package com.example.nexusplannerandroid.data.local.repository

import com.example.nexusplannerandroid.data.local.AppDataBase
import com.example.nexusplannerandroid.data.local.models.MisProyectosModel
import com.example.nexusplannerandroid.data.local.models.OtrosProyectosModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProyectoRepository @Inject constructor(
    private val db: AppDataBase
) {
    suspend fun insertMisProyectos(proyecto: MisProyectosModel){
        db.proyectoDao.insertMisProyectos(proyecto)
    }

    suspend fun deleteMisProyectos(proyecto: MisProyectosModel){
        db.proyectoDao.deleteMisProyectos(proyecto)
    }

    suspend fun getMisProyectos(): Flow<List<MisProyectosModel>> {
        return db.proyectoDao.getMisProyectos()
    }

    suspend fun getMisProyectosById(id: Int): Flow<MisProyectosModel?> {
        return db.proyectoDao.getMisProyectosById(id)
    }

    suspend fun truncateTableMisProyectos() {
        return db.proyectoDao.truncateTableMisProyectos()
    }


    suspend fun insertOtrosProyectos(proyecto: OtrosProyectosModel){
        db.proyectoDao.insertOtrosProyectos(proyecto)
    }

    suspend fun deleteOtrosProyectos(proyecto: OtrosProyectosModel){
        db.proyectoDao.deleteOtrosProyectos(proyecto)
    }

    suspend fun getOtrosProyectos(): Flow<List<OtrosProyectosModel>> {
        return db.proyectoDao.getOtrosProyectos()
    }

    suspend fun getOtrosProyectosById(id: Int): Flow<OtrosProyectosModel?> {
        return db.proyectoDao.getOtrosProyectosById(id)
    }

    suspend fun truncateTableOtrosProyectos() {
        return db.proyectoDao.truncateTableOtrosProyectos()
    }
}