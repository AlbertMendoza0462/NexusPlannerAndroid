package com.example.nexusplannerandroid.ui.intro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.nexusplannerandroid.data.local.repository.UsuarioRepository
import com.example.nexusplannerandroid.data.remote.dto.UsuarioDto
import com.example.nexusplannerandroid.ui.navegacion.NavegacionViewModel
import com.example.nexusplannerandroid.utils.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    var sesionIniciada by mutableStateOf(false)
    var enableSubmit by mutableStateOf(false)

    var yaSeCargo by mutableStateOf(false)

    fun onSiguiente(navController: NavController) {
        if (sesionIniciada) {
            navController.navigate(Screen.BienvenidaScreen.Route)
        } else {
            navController.navigate(Screen.LoginScreen.Route)
        }
    }

    fun onInit(navegacionViewModel: NavegacionViewModel, navController: NavController) {
        yaSeCargo = true
        viewModelScope.launch {
            val user = usuarioRepository.getFirstUsuario().first()
            if (user != null) {
                navegacionViewModel.sincronizarEntorno(
                    UsuarioDto(
                        usuarioId = user.usuarioId,
                        nombre = user.nombre,
                        apellido = user.apellido,
                        correo = user.correo,
                        telefono = user.telefono,
                        estado = user.estado,
                        proyectos = null
                    )
                )
                sesionIniciada = true
                println("Tenia sesion iniciada")
            } else {
                println("No tenia sesion iniciada")
            }
            enableSubmit = true
        }
    }
}