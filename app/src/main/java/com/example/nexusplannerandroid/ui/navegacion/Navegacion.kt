package com.example.nexusplannerandroid.ui.navegacion

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nexusplannerandroid.*
import com.example.nexusplannerandroid.R
import com.example.nexusplannerandroid.ui.Tarea.TareaScreen
import com.example.nexusplannerandroid.ui.principal.PrincipalScreen
import com.example.nexusplannerandroid.ui.proyecto.ProyectoScreen
import com.example.nexusplannerandroid.ui.theme.DancingScript
import com.example.nexusplannerandroid.utils.Constantes
import com.example.nexusplannerandroid.utils.Screen
import com.example.nexusplannerandroid.utils.validString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navegacion(
    navegacionViewModel: NavegacionViewModel = hiltViewModel()
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    if (!navegacionViewModel.withNavigationDrawer) {
        NavHostComponent(
            navController,
            navegacionViewModel.startDestination,
            navegacionViewModel,
            drawerState,
            scope
        )
    } else {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                DrawerContent(
                    scope,
                    navegacionViewModel.items,
                    navegacionViewModel.selectedItem,
                    drawerState,
                    navController,
                    navegacionViewModel
                )
            },
            content = {
                Scaffold(
                    content = {
                        it
                        Column(modifier = Modifier.fillMaxSize()) {
                            NavHostComponent(
                                navController,
                                navegacionViewModel.startDestination,
                                navegacionViewModel,
                                drawerState,
                                scope
                            )
                        }
                    }
                )
            }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavHostComponent(
    navController: NavHostController,
    startDestination: String,
    navegacionViewModel: NavegacionViewModel,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    Column(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable(route = Screen.IntroScreen.Route) {
                navegacionViewModel.withNavigationDrawer = Screen.IntroScreen.MostrarNav
                IntroScreen(
                    navController = navController,
                    navegacionViewModel = navegacionViewModel
                )
            }
            composable(route = Screen.LoginScreen.Route) {
                navegacionViewModel.withNavigationDrawer = Screen.LoginScreen.MostrarNav
                LoginScreen(
                    navController = navController,
                    navegacionViewModel = navegacionViewModel,
                    drawerState = drawerState,
                    scope = scope
                )
            }
            composable(route = Screen.RegistroScreen.Route + "/{correo}/{clave}") { navEntry ->
                navegacionViewModel.withNavigationDrawer = Screen.RegistroScreen.MostrarNav
                val correo = navEntry.arguments?.getString("correo") ?: "0"
                val clave = navEntry.arguments?.getString("clave") ?: "0"
                RegistroScreen(
                    navController = navController,
                    navegacionViewModel = navegacionViewModel,
                    drawerState = drawerState,
                    scope = scope,
                    correo = correo,
                    clave = clave
                )
            }
            composable(route = Screen.BienvenidaScreen.Route) {
                navegacionViewModel.withNavigationDrawer = Screen.BienvenidaScreen.MostrarNav
                BienvenidaScreen(
                    navController = navController,
                    navegacionViewModel = navegacionViewModel
                )
            }
            composable(route = Screen.FelicitacionScreen.Route + "/{id}/{estado}") {navEntry ->
                val id = navEntry.arguments?.getString("id")?.toInt()
                val estado = navEntry.arguments?.getString("estado")?.toInt()
                navegacionViewModel.withNavigationDrawer = Screen.FelicitacionScreen.MostrarNav
                FelicitacionScreen(
                    navController = navController,
                    navegacionViewModel = navegacionViewModel,
                    id = id,
                    estado = estado
                )
            }
            composable(route = Screen.PrincipalScreen.Route) {
                navegacionViewModel.withNavigationDrawer = Screen.PrincipalScreen.MostrarNav
                PrincipalScreen(
                    navController = navController,
                    navegacionViewModel = navegacionViewModel,
                    drawerState = drawerState,
                    scope = scope
                )
            }
            composable(route = Screen.ProyectoScreen.Route + "/{id}/{esPropio}") {navEntry ->
                val id = navEntry.arguments?.getString("id")?.toInt()
                val esPropio = navEntry.arguments?.getString("esPropio")?.toBoolean()
                navegacionViewModel.withNavigationDrawer = Screen.ProyectoScreen.MostrarNav
                ProyectoScreen(
                    navController = navController,
                    navegacionViewModel = navegacionViewModel,
                    drawerState = drawerState,
                    scope = scope,
                    id = id,
                    esPropio = esPropio
                )
            }
            composable(route = Screen.TareaScreen.Route + "/{id}") {navEntry ->
                val id = navEntry.arguments?.getString("id")?.toInt()
                navegacionViewModel.withNavigationDrawer = Screen.TareaScreen.MostrarNav
                TareaScreen(
                    navController = navController,
                    navegacionViewModel = navegacionViewModel,
                    drawerState = drawerState,
                    scope = scope,
                    id = id
                )
            }
//        composable(route = Screen.RegistroPerfilScreen.Route + "/{id}") { navEntry ->
//            val id = navEntry.arguments?.getString("id") ?: "0"
//            RegistroClienteScreen(
//                navController = navController, navegacionViewModel = navegacionViewModel,
//                id = id.toInt(),
//                viewModel = citaViewModel,
//                drawerState = drawerState,
//                scope = scope
//            )
//        }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerContent(
    scope: CoroutineScope,
    items: List<ItemNav>,
    selectedItem: ItemNav,
    drawerState: DrawerState,
    navController: NavHostController,
    navegacionViewModel: NavegacionViewModel
) {
    val activity = (LocalContext.current as? Activity)
    val nombreCompleto =
        validString(navegacionViewModel.usuario?.nombre) + " " + validString(navegacionViewModel.usuario?.apellido)

    ModalDrawerSheet {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(Modifier.height(50.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(),
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
            }
            Spacer(Modifier.height(50.dp))
            items.forEach { item ->
                NavigationDrawerItem(
                    icon = { Icon(item.icono, contentDescription = null) },
                    label = { Text(item.descripcion) },
                    selected = item == selectedItem,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(item.screen.Route)
                        navegacionViewModel.onChangeSelectedItem(item)
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
            NavigationDrawerItem(
                icon = { Icon(Icons.Default.Close, contentDescription = null) },
                label = { Text("Cerrar Sesion") },
                selected = false,
                onClick = {
                    navegacionViewModel.onCerrarSesion(navController)
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
            NavigationDrawerItem(
                icon = { Icon(Icons.Default.Logout, contentDescription = null) },
                label = { Text("Salir") },
                selected = false,
                onClick = {
                    scope.launch { drawerState.close() }
                    activity?.finish()
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(scope: CoroutineScope, selectedItem: ItemNav, drawerState: DrawerState) {
    CenterAlignedTopAppBar(
        title = {
            if (selectedItem.descripcion == "Principal")
                Text(
                    selectedItem.titulo,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            else
                Text(
                    selectedItem.titulo,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}