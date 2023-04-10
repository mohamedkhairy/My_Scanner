package com.example.myscanner.navigation

import androidx.navigation.NamedNavArgument

sealed class Screen(val route: String, val arguments: List<NamedNavArgument>){

    object Handla: Screen(
        route = "Handla",
        arguments = emptyList()
    )

    object Scanner: Screen(
        route = "Scanner",
        arguments = emptyList()
    )
}