package com.example.nexusplannerandroid.utils

sealed class Constantes(val value: String) {
    object NombreEmpresa: Constantes("Nexus Planner")
    // object ApiLink: Constantes("http://10.0.2.2:5298/api/")
    object ApiLink: Constantes("http://nexusplanner.somee.com/api/")
}