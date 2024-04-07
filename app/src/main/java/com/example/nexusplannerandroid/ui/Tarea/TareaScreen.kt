package com.example.nexusplannerandroid.ui.Tarea

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun TareaScreen(
    navController: NavController,
    navegacionViewModel: NavegacionViewModel,
    viewModel: TareaViewModel = hiltViewModel(),
    drawerState: DrawerState,
    scope: CoroutineScope,
    id: Int?
) {
    if (id != null)
        viewModel.sincronizarTarea(id)

    val localContext = LocalContext.current
    Scaffold(
        topBar = {
            TopBar(
                scope,
                navegacionViewModel.selectedItem,
                drawerState
            )
        }
    ) { paddingValues ->
        paddingValues
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
                fontSize = 20.sp
            )
            Text(
                text = viewModel.tarea?.nombre ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = 34.sp
            )
            Text(
                text = viewModel.tarea?.descripcion ?: "",
                fontSize = 20.sp
            )
            Text(
                text = "Fecha de finalización: " + (viewModel.tarea?.fechaFinal ?: ""),
                fontSize = 16.sp
            )
            Text(
                text = "Acciones",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            FlowRow(
                Modifier.fillMaxWidth()
            ) {
                if (viewModel.tarea?.estado == 1) {
                    Button(
                        modifier = Modifier.padding(12.dp),
                        contentPadding = PaddingValues(20.dp),
                        onClick = { viewModel.onIniciar(navegacionViewModel, navController) }) {
                        Row() {
                            Icon(
                                imageVector = Icons.Rounded.PlayArrow,
                                contentDescription = ""
                            )
                            Text(text = "Iniciar", fontSize = 20.sp)
                        }
                    }
                }
                if (viewModel.tarea?.estado == 3 || viewModel.tarea?.estado == 5) {
                    Button(
                        modifier = Modifier.padding(12.dp),
                        contentPadding = PaddingValues(20.dp),
                        onClick = { viewModel.onReanudar(navegacionViewModel, navController) }) {
                        Row() {
                            Icon(
                                imageVector = Icons.Rounded.PlayArrow,
                                contentDescription = ""
                            )
                            Text(text = "Reanudar", fontSize = 20.sp)
                        }
                    }
                }
                if (viewModel.tarea?.estado == 4) {
                    Button(
                        modifier = Modifier.padding(12.dp),
                        contentPadding = PaddingValues(20.dp),
                        onClick = { viewModel.onPausar(navegacionViewModel, navController) }) {
                        Row() {
                            Icon(
                                imageVector = Icons.Rounded.Pause,
                                contentDescription = ""
                            )
                            Text(text = "Pausar", fontSize = 20.sp)
                        }
                    }
                    Button(
                        modifier = Modifier.padding(12.dp),
                        contentPadding = PaddingValues(20.dp),
                        onClick = { viewModel.onTerminar(navegacionViewModel, navController) }) {
                        Row() {
                            Icon(
                                imageVector = Icons.Rounded.Check,
                                contentDescription = ""
                            )
                            Text(text = "Terminar", fontSize = 20.sp)
                        }
                    }
                }
            }
        }
    }
}

