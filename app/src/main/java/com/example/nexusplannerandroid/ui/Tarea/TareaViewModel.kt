package com.example.nexusplannerandroid.ui.Tarea

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.nexusplannerandroid.data.local.models.*
import com.example.nexusplannerandroid.data.local.repository.*
import com.example.nexusplannerandroid.data.remote.api_repository.TareaApiRepository
import com.example.nexusplannerandroid.ui.navegacion.NavegacionViewModel
import com.example.nexusplannerandroid.utils.Screen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TareaViewModel @Inject constructor(
    private val tareaRepository: TareaRepository,
    private val tareaApiRepository: TareaApiRepository,
    private val proyectoRepository: ProyectoRepository,
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {
    var proyecto by mutableStateOf<MisProyectosModel?>(null)
    var tarea by mutableStateOf<TareaModel?>(null)
    var usuario by mutableStateOf<UsuarioModel?>(null)

    fun sincronizarTarea(id: Int) {
        viewModelScope.launch {
            val t = tareaRepository.getTareaById(id).first()
            if (t != null) {
                tarea = t
                sincronizarProyecto(t.proyectoId)
                sincronizarUsuario(t.usuarioId)
            }
        }
    }

    fun sincronizarProyecto(id: Int) {
        viewModelScope.launch {
            val t = proyectoRepository.getMisProyectosById(id).first()
            if (t != null) {
                proyecto = t
            }
        }
    }

    fun sincronizarUsuario(id: Int) {
        viewModelScope.launch {
            val t = usuarioRepository.getFirstUsuario().first()
            if (t != null) {
                usuario = t
            }
        }
    }

    fun onIniciar(navegacionViewModel: NavegacionViewModel, navController: NavController) {
        viewModelScope.launch {
            if (tareaApiRepository.iniciarTarea(tarea?.tareaId.toString())) {
                tareaRepository.insertTarea(tarea!!)
                if (usuario != null)
                    navegacionViewModel.sincronizarEntorno(usuario!!)
                navController.navigate(Screen.FelicitacionScreen.Route + "/" + tarea?.tareaId + "/4")
            }
        }
    }

    fun onReanudar(navegacionViewModel: NavegacionViewModel, navController: NavController) {
        viewModelScope.launch {
            if (tareaApiRepository.reanudarTarea(tarea?.tareaId.toString())) {
                tareaRepository.insertTarea(tarea!!)
                if (usuario != null)
                    navegacionViewModel.sincronizarEntorno(usuario!!)
                navController.navigate(Screen.FelicitacionScreen.Route + "/" + tarea?.tareaId + "/1")
            }
        }
    }

    fun onPausar(navegacionViewModel: NavegacionViewModel, navController: NavController) {
        viewModelScope.launch {
            if (tareaApiRepository.pausarTarea(tarea?.tareaId.toString())) {
                tareaRepository.insertTarea(tarea!!)
                if (usuario != null)
                    navegacionViewModel.sincronizarEntorno(usuario!!)
                navController.navigate(Screen.FelicitacionScreen.Route + "/" + tarea?.tareaId + "/5")
            }
        }
    }

    fun onTerminar(navegacionViewModel: NavegacionViewModel, navController: NavController) {
        viewModelScope.launch {
            if (tareaApiRepository.terminarTarea(tarea?.tareaId.toString())) {
                tareaRepository.insertTarea(tarea!!)
                if (usuario != null)
                    navegacionViewModel.sincronizarEntorno(usuario!!)
                navController.navigate(Screen.FelicitacionScreen.Route + "/" + tarea?.tareaId + "/3")
            }
        }
    }
}