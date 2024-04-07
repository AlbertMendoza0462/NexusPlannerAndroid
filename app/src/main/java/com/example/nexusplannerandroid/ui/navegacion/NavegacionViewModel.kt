package com.example.nexusplannerandroid.ui.navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.nexusplannerandroid.data.local.models.*
import com.example.nexusplannerandroid.data.local.repository.*
import com.example.nexusplannerandroid.data.remote.api_repository.*
import com.example.nexusplannerandroid.data.remote.dto.UsuarioDto
import com.example.nexusplannerandroid.utils.Constantes
import com.example.nexusplannerandroid.utils.Screen
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavegacionViewModel @Inject constructor(
    private val entornoRepository: EntornoRepository,
    private val usuarioRepository: UsuarioRepository,
    private val proyectoRepository: ProyectoRepository,
    private val tareaRepository: TareaRepository,
    private val usuarioApiRepository: UsuarioApiRepository,
    private val proyectoApiRepository: ProyectoApiRepository,
    private val tareaApiRepository: TareaApiRepository
) : ViewModel() {
    var usuario by mutableStateOf<UsuarioModel?>(null)
    var entorno by mutableStateOf<EntornoModel?>(null)
    var proyectoSeleccionado by mutableStateOf<MisProyectosModel?>(null)
    var tareaSeleccionada by mutableStateOf<TareaModel?>(null)
    var withNavigationDrawer by mutableStateOf(true)
    var estaEntornoSincronizado by mutableStateOf(false)
    var estaUsuarioSincronizado by mutableStateOf(false)
    var estaMisProyectosSincronizado by mutableStateOf(false)
    var estaOtrosProyectosSincronizado by mutableStateOf(false)
    var estaTareasSincronizado by mutableStateOf(false)


    val items = mutableListOf<ItemNav>(
        ItemNav(
            descripcion = "Principal",
            icono = Icons.Default.Home,
            screen = Screen.PrincipalScreen,
            titulo = Constantes.NombreEmpresa.value
        ),
//        ItemNav(
//            descripcion = "Solicitudes",
//            icono = Icons.Default.Schedule,
//            screen = Screen.ConsultaCitasPendientesScreen
//        ),
//        ItemNav(
//            descripcion = "Cuenta",
//            icono = Icons.Default.EventAvailable,
//            screen = Screen.ConsultaHistorialCitasScreen
//        )
    )

    fun sincronizarEntorno(usuarioDto: UsuarioDto) {
        viewModelScope.launch {
            entornoRepository.insertEntorno(
                EntornoModel(
                    entornoId = 1,
                    correoComun = usuarioDto.correo,
                    esPrimerInicioDeSesion = true
                )
            )
            println("sincronizarEntorno")
            sincronizarUsuarioApi(usuarioDto)
            sincronizarMisProyectosApi(usuarioDto.usuarioId)
            sincronizarOtrosProyectosApi(usuarioDto.usuarioId)
            sincronizarTareasApi(usuarioDto.usuarioId)

            estaEntornoSincronizado = true
        }
    }

    fun sincronizarEntorno(usuarioModel: UsuarioModel) {
        viewModelScope.launch {
            entornoRepository.insertEntorno(
                EntornoModel(
                    entornoId = 1,
                    correoComun = usuarioModel.correo,
                    esPrimerInicioDeSesion = true
                )
            )
            println("sincronizarEntorno")
            sincronizarUsuarioApi(
                UsuarioDto(
                usuarioModel.usuarioId,
                usuarioModel.nombre,
                usuarioModel.apellido,
                usuarioModel.correo,
                usuarioModel.telefono,
                usuarioModel.estado,
                null
            )
            )
            sincronizarMisProyectosApi(usuarioModel.usuarioId)
            sincronizarOtrosProyectosApi(usuarioModel.usuarioId)
            sincronizarTareasApi(usuarioModel.usuarioId)

            estaEntornoSincronizado = true
        }
    }

    fun onCerrarSesion(navController: NavController) {
        viewModelScope.launch {
            usuarioRepository.truncateTableUsuario()
            proyectoRepository.truncateTableMisProyectos()
            proyectoRepository.truncateTableOtrosProyectos()
            tareaRepository.truncateTableTarea()
            navController.enableOnBackPressed(false)
            navController.navigate(Screen.LoginScreen.Route)
        }
    }

    fun sincronizarUsuarioApi(usuarioDto: UsuarioDto) {
        viewModelScope.launch {
            println("sincronizarUsuarioApi")
            usuarioRepository.truncateTableUsuario()
            usuarioRepository.insertUsuario(
                UsuarioModel(
                    usuarioDto.usuarioId,
                    usuarioDto.nombre,
                    usuarioDto.apellido,
                    usuarioDto.correo,
                    usuarioDto.telefono,
                    usuarioDto.estado
                )
            )
        }
    }

    fun sincronizarMisProyectosApi(usuarioId: Int) {
        viewModelScope.launch {
            println("sincronizarMisPriyectosApi")
            var misProyectosApi = proyectoApiRepository.getMisProyectos(usuarioId.toString())

            proyectoRepository.truncateTableMisProyectos()

            misProyectosApi.forEach {
                proyectoRepository.insertMisProyectos(
                    MisProyectosModel(
                        it.proyectoId,
                        it.usuarioId,
                        it.nombre,
                        it.descripcion,
                        it.fechaFinal,
                        it.fechaCreacion,
                        it.estado
                    )
                )
            }
        }
    }

    fun sincronizarOtrosProyectosApi(usuarioId: Int) {
        viewModelScope.launch {
            println("sincronizarOtrosProyectosApi")
            var otrosProyectosApi = proyectoApiRepository.getOtrosProyectos(usuarioId.toString())

            proyectoRepository.truncateTableOtrosProyectos()

            otrosProyectosApi.forEach {
                proyectoRepository.insertOtrosProyectos(
                    OtrosProyectosModel(
                        it.proyectoId,
                        it.usuarioId,
                        it.nombre,
                        it.descripcion,
                        it.fechaFinal,
                        it.fechaCreacion,
                        it.estado
                    )
                )
            }
        }
    }

    fun sincronizarTareasApi(usuarioId: Int) {
        viewModelScope.launch {
            println("sincronizarTareasApi")
            var tareaApi = tareaApiRepository.getTareas(usuarioId.toString())

            tareaRepository.truncateTableTarea()

            tareaApi.forEach {
                tareaRepository.insertTarea(
                    TareaModel(
                        it.tareaId,
                        it.proyectoId,
                        it.usuarioId,
                        it.nombre,
                        it.descripcion,
                        it.fechaFinal,
                        it.fechaCreacion,
                        it.estado
                    )
                )
            }
        }
    }

    var selectedItem by mutableStateOf(items[0])
    var mostrarNav by mutableStateOf(false)

    var startDestination = Screen.IntroScreen.Route

    fun onChangeSelectedItem(itemNav: ItemNav) {
        selectedItem = itemNav
    }

    fun onChangeMostrarNav(t: Boolean) {
        if (!t) selectedItem = items.get(0)
        mostrarNav = t
    }

}

class ItemNav(
    val descripcion: String,
    val icono: ImageVector,
    val screen: Screen,
    val titulo: String = descripcion
)