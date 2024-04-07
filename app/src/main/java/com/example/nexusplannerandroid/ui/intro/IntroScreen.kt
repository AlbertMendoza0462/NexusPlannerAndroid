package com.example.nexusplannerandroid

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nexusplannerandroid.ui.intro.IntroViewModel
import com.example.nexusplannerandroid.ui.navegacion.NavegacionViewModel
import com.example.nexusplannerandroid.ui.theme.DancingScript
import com.example.nexusplannerandroid.utils.Constantes
import com.example.nexusplannerandroid.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntroScreen(
    navController: NavController,
    navegacionViewModel: NavegacionViewModel,
    viewModel: IntroViewModel = hiltViewModel()
) {
    if (!viewModel.yaSeCargo) {
        viewModel.yaSeCargo = true
        viewModel.onInit(navegacionViewModel, navController)
    }

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
                    enabled = viewModel.enableSubmit,
                    onClick = { viewModel.onSiguiente(navController) }) {
                    Row() {
                        Text(text = "Vamos a empezar", fontSize = 20.sp)
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
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = Constantes.NombreEmpresa.value,
                textAlign = TextAlign.Center,
                lineHeight = 56.sp,
                fontSize = 56.sp,
                fontFamily = DancingScript,
                modifier = Modifier.width(312.dp)
            )
            Spacer(modifier = Modifier.height(62.dp))
            Text(
                text = "Diseñado para equipos que aspiran a la excelencia.",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                modifier = Modifier.width(312.dp)
            )
        }
    }

}