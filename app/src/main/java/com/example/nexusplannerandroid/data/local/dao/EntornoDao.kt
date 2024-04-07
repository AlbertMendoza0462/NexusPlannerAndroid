package com.example.nexusplannerandroid.data.local.dao

import androidx.room.*
import com.example.nexusplannerandroid.data.local.models.EntornoModel
import kotlinx.coroutines.flow.Flow

@Dao
interface EntornoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntorno(entorno: EntornoModel)

    @Query("Select * from Entorno limit 1")
    fun getEntorno(): Flow<EntornoModel?>
}