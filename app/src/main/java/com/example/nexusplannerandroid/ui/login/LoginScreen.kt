package com.example.nexusplannerandroid

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nexusplannerandroid.ui.login.LoginViewModel
import com.example.nexusplannerandroid.ui.navegacion.NavegacionViewModel
import com.example.nexusplannerandroid.ui.theme.DancingScript
import com.example.nexusplannerandroid.ui.theme.extendedDark
import com.example.nexusplannerandroid.utils.Constantes
import com.example.nexusplannerandroid.utils.Screen
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    navegacionViewModel: NavegacionViewModel,
    viewModel: LoginViewModel = hiltViewModel(),
    drawerState: DrawerState,
    scope: CoroutineScope
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
                    label = { Text(text = "Correo") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    value = viewModel.correo,
                    onValueChange = { viewModel.onCorreoChange(it) })
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "Clave") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    value = viewModel.clave,
                    onValueChange = { viewModel.onClaveChange(it) })
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
                        onClick = { viewModel.onHandleCrearUsuario(navController) }
                    ) {
                        Text(text = "Crear una cuenta", fontSize = 20.sp)
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
                            onClick = {
                                viewModel.onHandleIniciarSesion(
                                    localContext,
                                    navController,
                                    navegacionViewModel
                                )
                            }
                        ) {
                            Text(text = "Inicia sesión", fontSize = 20.sp, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}