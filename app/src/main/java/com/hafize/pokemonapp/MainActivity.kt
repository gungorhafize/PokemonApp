package com.hafize.pokemonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hafize.pokemonapp.ui.Screen
import com.hafize.pokemonapp.ui.pokemondetail.PokemonDetailScreen
import com.hafize.pokemonapp.ui.pokemonlist.PokemonListScreen
import com.hafize.pokemonapp.ui.theme.PokemonAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonAppTheme {
                val navController = rememberNavController()
                Surface {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route
                    ) {
                        composable(Screen.Home.route) {
                            PokemonListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.Detail.route,
                            arguments = Screen.Detail.navArguments
                        ) {
                            val dominantColor = remember {
                                val color = it.arguments?.getInt("dominantColor")
                                color?.let { Color(it) } ?: Color.White
                            }
                            val pokemonName = remember {
                                it.arguments?.getString("pokemonName")
                            }
                            val pokemonNum = remember {
                                it.arguments?.getInt("pokemonNum")
                            }
                            PokemonDetailScreen(
                                dominantColor = dominantColor.toArgb(),
                                name = pokemonName?.toLowerCase(Locale.ROOT) ?: "",
                                number = pokemonNum ?: 0,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
