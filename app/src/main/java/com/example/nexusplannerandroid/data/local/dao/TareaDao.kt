package com.example.nexusplannerandroid.data.local.dao

import androidx.room.*
import com.example.nexusplannerandroid.data.local.models.TareaModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TareaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTarea(tarea: TareaModel)

    @Query("Select * from Tareas") // Añadir condicion que solo traiga los del status 1
    fun getTareas(): Flow<List<TareaModel>>

    @Query("Select * from Tareas where tareaId=:id")
    fun getTareaById(id:Int): Flow<TareaModel?>

    @Delete
    fun deleteTarea(tarea: TareaModel)

    @Query("Delete from tareas")
    fun truncateTableTareas()
}