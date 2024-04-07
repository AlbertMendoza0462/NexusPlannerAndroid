package com.example.nexusplannerandroid.data.local.dao

import androidx.room.*
import com.example.nexusplannerandroid.data.local.models.MisProyectosModel
import com.example.nexusplannerandroid.data.local.models.OtrosProyectosModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ProyectoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMisProyectos(proyecto: MisProyectosModel)

    @Query("Select * from MisProyectos")
    fun getMisProyectos(): Flow<List<MisProyectosModel>>

    @Query("Select * from MisProyectos where proyectoId=:id")
    fun getMisProyectosById(id:Int): Flow<MisProyectosModel?>

    @Delete
    fun deleteMisProyectos(proyecto: MisProyectosModel)

    @Query("Delete from MisProyectos")
    fun truncateTableMisProyectos()




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOtrosProyectos(proyecto: OtrosProyectosModel)

    @Query("Select * from OtrosProyectos")
    fun getOtrosProyectos(): Flow<List<OtrosProyectosModel>>

    @Query("Select * from OtrosProyectos where proyectoId=:id")
    fun getOtrosProyectosById(id:Int): Flow<OtrosProyectosModel?>

    @Delete
    fun deleteOtrosProyectos(proyecto: OtrosProyectosModel)

    @Query("Delete from OtrosProyectos")
    fun truncateTableOtrosProyectos()
}