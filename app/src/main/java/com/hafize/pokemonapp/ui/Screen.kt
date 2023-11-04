package com.hafize.pokemonapp.ui

import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

// Define a sealed class representing screens in the app with a route identifier.
sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    // Create a Home screen object with the route identifier "pokemon_list_screen."
    object Home : Screen(
        route = "pokemon_list_screen"

    )

    object Detail : Screen(
        route = "pokemon_detail_screen/{dominantColor}/{pokemonName}/{pokemonNum}",
        navArguments = listOf(
            navArgument("dominantColor") {
                type = NavType.IntType
            },
            navArgument("pokemonName") {
                type = NavType.StringType
            },
            navArgument("pokemonNum") {
                type = NavType.IntType
            }
        )
    )
}
