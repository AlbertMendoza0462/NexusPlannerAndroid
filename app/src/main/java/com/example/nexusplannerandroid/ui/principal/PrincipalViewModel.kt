package com.example.nexusplannerandroid.ui.principal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.nexusplannerandroid.data.local.models.*
import com.example.nexusplannerandroid.data.local.repository.ProyectoRepository
import com.example.nexusplannerandroid.data.local.repository.TareaRepository
import com.example.nexusplannerandroid.ui.navegacion.NavegacionViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PrincipalViewModel @Inject constructor(
    private val proyectoRepository: ProyectoRepository,
    private val tareaRepository: TareaRepository

) : ViewModel() {
    var misProyectos by mutableStateOf(emptyList<MisProyectosModel>())
    var otrosProyectos by mutableStateOf(emptyList<OtrosProyectosModel>())
    var tareas by mutableStateOf(emptyList<TareaModel>())

    init {
        sincronizarMisProyectos()
        sincronizarOtrosProyectos()
        sincronizarTareas()
    }

    fun sincronizarMisProyectos() {
        viewModelScope.launch {
            proyectoRepository.getMisProyectos().collect {
                misProyectos = it
            }
        }
    }

    fun sincronizarOtrosProyectos() {
        viewModelScope.launch {
            proyectoRepository.getOtrosProyectos().collect {
                otrosProyectos = it
            }
        }
    }

    fun sincronizarTareas() {
        viewModelScope.launch {
            tareaRepository.getTareas().collect {
                tareas = it
            }
        }
    }
}