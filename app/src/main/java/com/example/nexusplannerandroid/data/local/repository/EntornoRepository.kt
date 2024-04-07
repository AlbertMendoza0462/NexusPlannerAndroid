package com.example.nexusplannerandroid.data.local.repository

import com.example.nexusplannerandroid.data.local.AppDataBase
import com.example.nexusplannerandroid.data.local.models.EntornoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EntornoRepository @Inject constructor(
    private val db: AppDataBase
) {

    suspend fun insertEntorno(entorno: EntornoModel){
        db.entornoDao.insertEntorno(entorno)
    }

    suspend fun getEntorno(): Flow<EntornoModel?> {
        return db.entornoDao.getEntorno()
    }
}