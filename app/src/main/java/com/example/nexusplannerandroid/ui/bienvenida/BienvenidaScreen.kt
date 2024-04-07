package com.example.nexusplannerandroid

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.rounded.ArrowRight
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
import com.example.nexusplannerandroid.ui.bienvenida.BienvenidaViewModel
import com.example.nexusplannerandroid.ui.intro.IntroViewModel
import com.example.nexusplannerandroid.ui.navegacion.NavegacionViewModel
import com.example.nexusplannerandroid.ui.theme.DancingScript
import com.example.nexusplannerandroid.utils.Constantes
import com.example.nexusplannerandroid.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BienvenidaScreen(
    navController: NavController,
    navegacionViewModel: NavegacionViewModel,
    viewModel: BienvenidaViewModel = hiltViewModel()
) {
    Scaffold(
        bottomBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(bottom = 100.dp)
                    .fillMaxWidth()
            ) {
                Button(
                    contentPadding = PaddingValues(20.dp),
                    onClick = { navController.navigate(Screen.PrincipalScreen.Route) }) {
                    Row() {
                        Text(text = "Siguiente", fontSize = 20.sp)
                        Icon(
                            imageVector = Icons.Rounded.ArrowRight,
                            contentDescription = "ArrowRight"
                        )
                    }
                }
            }
        }
    ) {
        it
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.nexusplannerlogo),
                contentDescription = "Imagen Intro"
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = buildAnnotatedString {
                    append("¡Bienvenido a ")
                    withStyle(
                        style = SpanStyle(
                            fontSize = 56.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append(Constantes.NombreEmpresa.value)
                    }
                    append(", tu aliado definitivo en la gestión de proyectos en equipo!")
                },
                textAlign = TextAlign.Center,
                lineHeight = 46.sp,
                fontSize = 46.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(42.dp)
            )
        }
    }

}