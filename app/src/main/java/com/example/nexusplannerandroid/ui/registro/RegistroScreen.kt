package com.example.nexusplannerandroid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nexusplannerandroid.ui.navegacion.NavegacionViewModel
import com.example.nexusplannerandroid.ui.registro.RegistroViewModel
import com.example.nexusplannerandroid.ui.theme.extendedDark
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(
    navController: NavController,
    navegacionViewModel: NavegacionViewModel,
    viewModel: RegistroViewModel = hiltViewModel(),
    drawerState: DrawerState,
    scope: CoroutineScope,
    correo: String,
    clave: String
) {
    val localContext = LocalContext.current
    Scaffold() {
        it
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 28.dp, start = 28.dp, bottom = 20.dp, top = 68.dp)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Sólo unos datos más...",
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    lineHeight = 48.sp,
                    fontSize = 48.sp,
                    modifier = Modifier.width(312.dp)
                )
                Spacer(modifier = Modifier.height(62.dp))
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(32.dp, 32.dp, 0.dp, 0.dp)
                    )
                    .padding(22.dp)
            ) {
                TextField(
                    label = { Text(text = "Nombre*") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    value = viewModel.nombre,
                    onValueChange = { viewModel.onNombreChange(it) })
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "Apellido*") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    value = viewModel.apellido,
                    onValueChange = { viewModel.onApellidoChange(it) })
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "Teléfono") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    value = viewModel.telefono,
                    onValueChange = { viewModel.onTelefonoChange(it) })
                Spacer(modifier = Modifier.height(20.dp))
                Column {
                    Button(
                        colors = ButtonColors(
                            containerColor = extendedDark.yellow.color,
                            contentColor = contentColorFor(backgroundColor = Color.Black),
                            disabledContainerColor = extendedDark.yellow.color,
                            disabledContentColor = contentColorFor(backgroundColor = Color.Black)
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = { viewModel.save(correo, clave, navController, navegacionViewModel) }
                    ) {
                        Text(text = "Crear", fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "O", fontSize = 20.sp, color = Color.White)
                        Button(
                            colors = ButtonColors(
                                containerColor = extendedDark.blueDark.colorContainer,
                                contentColor = contentColorFor(backgroundColor = Color.White),
                                disabledContainerColor = extendedDark.blueDark.colorContainer,
                                disabledContentColor = contentColorFor(backgroundColor = Color.White)
                            ),
                            onClick = { navController.popBackStack() }
                        ) {
                            Text(text = "Iniciar sesión", fontSize = 20.sp, color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Al registrarse acepta nuestras condiciones de uso y políticas de privacidad.",
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
        }
    }
}