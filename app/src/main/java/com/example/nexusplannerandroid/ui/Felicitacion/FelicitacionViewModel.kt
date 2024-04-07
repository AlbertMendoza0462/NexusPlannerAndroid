package com.example.nexusplannerandroid.ui.Felicitacion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.nexusplannerandroid.data.local.models.TareaModel
import com.example.nexusplannerandroid.data.local.models.UsuarioModel
import com.example.nexusplannerandroid.data.local.repository.TareaRepository
import com.example.nexusplannerandroid.data.local.repository.UsuarioRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FelicitacionViewModel @Inject constructor(
    private val tareaRepository: TareaRepository,
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {
    var tarea by mutableStateOf<TareaModel?>(null)
    var usuario by mutableStateOf<UsuarioModel?>(null)

    fun sincronizarTarea(id: Int) {
        viewModelScope.launch {
            val t = tareaRepository.getTareaById(id).first()
            if (t != null) {
                tarea = t
                sincronizarUsuario(t.usuarioId)
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
}