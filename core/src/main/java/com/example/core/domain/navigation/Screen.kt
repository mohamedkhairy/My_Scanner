package com.example.core.domain.navigation


sealed class Screen(val route: String){

    object Handla: Screen(route = "Handla")

    object Scanner: Screen(route = "Scanner")
}