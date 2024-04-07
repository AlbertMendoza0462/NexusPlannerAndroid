package com.example.nexusplannerandroid.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Entorno")
data class EntornoModel(
    @PrimaryKey
    val entornoId: Int,
    val correoComun: String,
    val esPrimerInicioDeSesion: Boolean
)
