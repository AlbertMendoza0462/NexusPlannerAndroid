package com.example.nexusplannerandroid.ui.proyecto

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.nexusplannerandroid.data.local.models.*
import com.example.nexusplannerandroid.data.local.repository.ProyectoRepository
import com.example.nexusplannerandroid.data.local.repository.TareaRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProyectoViewModel @Inject constructor(
    private val tareaRepository: TareaRepository,
    private val proyectoRepository: ProyectoRepository
) : ViewModel() {
    var proyecto by mutableStateOf<MisProyectosModel?>(null)
    var tareas by mutableStateOf(emptyList<TareaModel>())

    fun sincronizarProyecto(id: Int, esPropio: Boolean?) {
        viewModelScope.launch {
            if (esPropio != null && !esPropio) {
                proyectoRepository.getOtrosProyectosById(id).collect {
                    if (it != null) {
                        proyecto = MisProyectosModel(
                            it.proyectoId,
                            it.usuarioId,
                            it.nombre,
                            it.descripcion,
                            it.fechaFinal,
                            it.fechaCreacion,
                            it.estado
                        )
                        sincronizarTareas()
                    }
                }
            } else {
                proyectoRepository.getMisProyectosById(id).collect {
                    proyecto = it
                    sincronizarTareas()
                }
            }
        }
    }

    fun sincronizarTareas() {
        viewModelScope.launch {
            tareaRepository.getTareas().collect {
                if (proyecto != null)
                    tareas = it.filter { it.proyectoId == proyecto?.proyectoId }
            }
        }
    }
}