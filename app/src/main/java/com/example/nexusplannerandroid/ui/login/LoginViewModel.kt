package com.example.nexusplannerandroid.ui.login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.nexusplannerandroid.data.local.models.LoginModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.nexusplannerandroid.data.local.repository.UsuarioRepository
import com.example.nexusplannerandroid.data.remote.api_repository.UsuarioApiRepository
import com.example.nexusplannerandroid.data.remote.dto.UsuarioDto
import com.example.nexusplannerandroid.ui.navegacion.NavegacionViewModel
import com.example.nexusplannerandroid.utils.Screen
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val api: UsuarioApiRepository,
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    var correo by mutableStateOf("")
    var clave by mutableStateOf("")

    var enableSubmit by mutableStateOf(true)

    fun onCorreoChange(t: String) {
        correo = t
    }

    fun onClaveChange(t: String) {
        clave = t
    }

    fun onHandleIniciarSesion(
        localContext: Context,
        navController: NavController,
        navegacionViewModel: NavegacionViewModel
    ) {
        iniciarSesion(
            localContext,
            navController,
            navegacionViewModel
        )
    }

    fun onHandleCrearUsuario(navController: NavController) {
        crearUsuario(navController)
    }

    fun crearUsuario(navController: NavController) {
        navController.navigate(Screen.RegistroScreen.Route + "/" + correo + "/" + clave)
    }

    fun iniciarSesion(
        localContext: Context,
        navController: NavController,
        navegacionViewModel: NavegacionViewModel,
        salirAlTerminar: Boolean = true
    ) {
        enableSubmit = false

        viewModelScope.launch {
            if (true) {
                val intentoGuardar = api.login(
                    LoginModel(
                        correo = correo,
                        clave = clave
                    )
                )

                if (
                    intentoGuardar != null
                ) {
                    navegacionViewModel.sincronizarEntorno(intentoGuardar)
                    if (salirAlTerminar)
                        navController.navigate(Screen.BienvenidaScreen.Route)
                } else {
                    Toast.makeText(localContext, "No se pudo iniciar sesion.", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(localContext, "Algo fallo", Toast.LENGTH_SHORT).show()
            }
            enableSubmit = true
        }
    }
}