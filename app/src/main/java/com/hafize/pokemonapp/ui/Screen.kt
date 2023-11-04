package com.hafize.pokemonapp.ui

// Define a sealed class representing screens in the app with a route identifier.
sealed class Screen(
    val route: String
) {
    // Create a Home screen object with the route identifier "pokemon_list_screen."
    object Home : Screen(
        route = "pokemon_list_screen"
    )

}
