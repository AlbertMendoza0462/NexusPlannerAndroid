package com.example.nexusplannerandroid.ui.registro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.nexusplannerandroid.data.local.models.NuevoUsuarioModel
import com.example.nexusplannerandroid.data.local.models.UsuarioModel
import com.example.nexusplannerandroid.data.local.repository.UsuarioRepository
import com.example.nexusplannerandroid.data.remote.api_repository.UsuarioApiRepository
import com.example.nexusplannerandroid.ui.navegacion.NavegacionViewModel
import com.example.nexusplannerandroid.utils.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroViewModel @Inject constructor(
    private val api: UsuarioApiRepository,
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {
    var nombre by mutableStateOf("")
    var apellido by mutableStateOf("")
    var telefono by mutableStateOf("")

    var enableSubmit by mutableStateOf(true)

    fun onNombreChange(t: String) {
        nombre = t
    }

    fun onApellidoChange(t: String) {
        apellido = t
    }

    fun onTelefonoChange(t: String) {
        telefono = t
    }

    fun save(correo: String, clave: String, navController: NavController, navegacionViewModel: NavegacionViewModel) {
        viewModelScope.launch {
            val guardo = api.insertUsuario(
                NuevoUsuarioModel(
                    usuarioId = 0,
                    nombre = nombre,
                    apellido = apellido,
                    correo = correo,
                    clave = clave,
                    telefono = telefono,
                    estado = 1
                )
            )
            if (guardo) {
                val user = UsuarioModel(
                    usuarioId = 0,
                    nombre = nombre,
                    apellido = apellido,
                    correo = correo,
                    telefono = telefono,
                    estado = 1
                )
                usuarioRepository.insertUsuario(user)
                navegacionViewModel.sincronizarEntorno(user)
                navController.navigate(Screen.BienvenidaScreen.Route)
            }
        }
    }
}