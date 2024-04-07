package com.example.nexusplannerandroid.ui.principal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nexusplannerandroid.R
import com.example.nexusplannerandroid.ui.navegacion.NavegacionViewModel
import com.example.nexusplannerandroid.ui.navegacion.TopBar
import com.example.nexusplannerandroid.ui.theme.DancingScript
import com.example.nexusplannerandroid.utils.Constantes
import com.example.nexusplannerandroid.utils.Screen
import com.example.nexusplannerandroid.utils.components.CardComponent
import kotlinx.coroutines.CoroutineScope

private class ItemNav(
    val Descripcion: String,
    val Icono: ImageVector,
    val Titulo: String = Descripcion
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalScreen(
    navController: NavController,
    navegacionViewModel: NavegacionViewModel,
    viewModel: PrincipalViewModel = hiltViewModel(),
    drawerState: DrawerState,
    scope: CoroutineScope
) {
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
        if (viewModel.misProyectos.isEmpty() && viewModel.otrosProyectos.isEmpty() && viewModel.tareas.isEmpty()) {
            EmptyPage()
        } else {
            Content(viewModel, navController)
        }
    }
}

@Composable
private fun Content(viewModel: PrincipalViewModel, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(end = 28.dp, start = 28.dp, bottom = 20.dp, top = 68.dp)
    ) {
        if (!viewModel.misProyectos.isEmpty()) {
            MisProyectos(viewModel, navController)
        }
        if (!viewModel.otrosProyectos.isEmpty()) {
            OtrosProyectos(viewModel, navController)
        }
        if (!viewModel.tareas.isEmpty()) {
            MisTareasPendientes(viewModel, navController)
        }
    }
}

@Composable
private fun MisProyectos(viewModel: PrincipalViewModel, navController: NavController) {
    Text(
        text = "Mis proyectos",
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
    Spacer(modifier = Modifier.height(24.dp))
    Column(Modifier.fillMaxWidth()) {
        viewModel.misProyectos.forEach {
            CardComponent(
                titulo = it.nombre,
                footer = "Terminará el " + it.fechaFinal,
                mensaje = it.descripcion,
                onClick = {
                    navController.navigate(Screen.ProyectoScreen.Route + "/" + it.proyectoId + "/true")
                }
            )
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
private fun OtrosProyectos(viewModel: PrincipalViewModel, navController: NavController) {
    Text(
        text = "Otros proyectos",
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
    Spacer(modifier = Modifier.height(24.dp))
    Column(Modifier.fillMaxWidth()) {
        viewModel.otrosProyectos.forEach {
            CardComponent(
                titulo = it.nombre,
                footer = "Terminará el " + it.fechaFinal,
                mensaje = it.descripcion,
                onClick = {
                    navController.navigate(Screen.ProyectoScreen.Route + "/" + it.proyectoId + "/false")
                }
            )
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}


@Composable
private fun MisTareasPendientes(viewModel: PrincipalViewModel, navController: NavController) {
    Text(
        text = "Mis tareas pendientes",
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
    Spacer(modifier = Modifier.height(24.dp))
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
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
private fun EmptyPage() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 28.dp, start = 28.dp, bottom = 20.dp, top = 68.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.nexusplannerlogo),
            contentDescription = "Imagen Intro"
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = Constantes.NombreEmpresa.value,
            textAlign = TextAlign.Center,
            lineHeight = 56.sp,
            fontSize = 56.sp,
            fontFamily = DancingScript,
            modifier = Modifier.width(312.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Puedes crear tus proyectos y tareas desde nuestra aplicación web!",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(42.dp)
        )
    }
}