package com.example.nexusplannerandroid

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nexusplannerandroid.ui.Felicitacion.FelicitacionViewModel
import com.example.nexusplannerandroid.ui.navegacion.NavegacionViewModel
import com.example.nexusplannerandroid.ui.theme.Lobster

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FelicitacionScreen(
    navController: NavController,
    navegacionViewModel: NavegacionViewModel,
    viewModel: FelicitacionViewModel = hiltViewModel(),
    id: Int?,
    estado: Int?
) {
    if (id != null)
        viewModel.sincronizarTarea(id.toInt())

    Scaffold(
        bottomBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(bottom = 70.dp)
                    .fillMaxWidth()
            ) {
                Button(
                    contentPadding = PaddingValues(20.dp),
                    onClick = { navController.popBackStack() }) {
                    Row() {
                        Icon(
                            imageVector = Icons.Rounded.ArrowLeft,
                            contentDescription = "ArrowLeft"
                        )
                        Text(text = "Regresar", fontSize = 20.sp)
                    }
                }
            }
        }
    ) {
        it
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.nexusplannerlogo),
                    contentDescription = "Imagen Intro"
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Excelente\n" + viewModel.usuario?.nombre + "!",
                    textAlign = TextAlign.Center,
                    lineHeight = 56.sp,
                    fontSize = 56.sp,
                    fontFamily = Lobster,
                    modifier = Modifier.width(312.dp)
                )
                var acciones = hashMapOf<Int, String>(
                    Pair(1, "reanudado"),
                    Pair(4, "iniciado"),
                    Pair(5, "pausado"),
                    Pair(3, "finalizado")
                )
                Text(
                    text = buildAnnotatedString {
                        append(
                            "¡Has " + (acciones[estado] ?: "editado") + " la tarea "
                        )
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            append(viewModel.tarea?.nombre)
                        }
                        append(", tu aliado definitivo en la gestión de proyectos en equipo!")
                    },
                    textAlign = TextAlign.Center,
                    lineHeight = 32.sp,
                    fontSize = 32.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp, 30.dp, 30.dp, 0.dp)
                )
                if (estado == 3) {
                    Text(
                        text = "El final exitoso del proyecto está a solo unas tareas de distancia.",
                        textAlign = TextAlign.Center,
                        lineHeight = 32.sp,
                        fontSize = 32.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp)
                    )
                } else {
                    Text(
                        text = buildAnnotatedString {
                            append("Recuerda que tienes hasta el ")
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colorScheme.primary
                                )
                            ) {
                                append(viewModel.tarea?.fechaFinal)
                            }
                            append(" para completarla.")
                        },
                        textAlign = TextAlign.Center,
                        lineHeight = 32.sp,
                        fontSize = 32.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp)
                    )
                }
            }
        }
    }

}