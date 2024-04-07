package com.example.nexusplannerandroid.ui.proyecto

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nexusplannerandroid.ui.navegacion.NavegacionViewModel
import com.example.nexusplannerandroid.ui.navegacion.TopBar
import com.example.nexusplannerandroid.utils.Screen
import com.example.nexusplannerandroid.utils.components.CardComponent
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProyectoScreen(
    navController: NavController,
    navegacionViewModel: NavegacionViewModel,
    viewModel: ProyectoViewModel = hiltViewModel(),
    drawerState: DrawerState,
    scope: CoroutineScope,
    id: Int?,
    esPropio: Boolean?
) {

    if (id != null)
        viewModel.sincronizarProyecto(id, esPropio)

    val localContext = LocalContext.current
    Scaffold(
        topBar = {
            TopBar(
                scope,
                navegacionViewModel.selectedItem,
                drawerState
            )
        }
    ) {
        it
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(end = 28.dp, start = 28.dp, bottom = 20.dp, top = 68.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = viewModel.proyecto?.nombre ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = 34.sp
            )
            Text(
                text = viewModel.proyecto?.descripcion ?: "",
                fontSize = 20.sp
            )
            Text(
                text = "Fecha de finalización: " + (viewModel.proyecto?.fechaFinal ?: ""),
                fontSize = 16.sp
            )
            if (!viewModel.tareas.isEmpty()) {
                MisTareasPendientes(viewModel, navController)
            }
        }
    }
}

@Composable
private fun MisTareasPendientes(viewModel: ProyectoViewModel, navController: NavController) {
    Text(
        text = "Mis tareas pendientes",
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
    Column(Modifier.fillMaxWidth()) {
        viewModel.tareas.forEach {
            CardComponent(
                titulo = it.nombre,
                footer = "Terminará el " + it.fechaFinal,
                mensaje = it.descripcion ?: "",
                status = it.estado,
                onClick = {
                    navController.navigate(Screen.TareaScreen.Route + "/" + it.tareaId)
                }
            )
        }
    }
}

