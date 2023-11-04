package com.hafize.pokemonapp.data.remote

import com.example.pokemon.data.remote.responses.PokemonList
import retrofit2.http.GET
import retrofit2.http.Query

// Define an interface for making HTTP requests to the PokeAPI.
interface PokeApi {
    // Declare an HTTP GET request to the "pokemon" endpoint.
    @GET("pokemon")
    // Define a suspend function to get a list of Pokémon.

    suspend fun getPokemonList(
        // Specify the "limit" query parameter to control the number of results.
        @Query("limit") limit: Int,
        // Specify the "offset" query parameter to control the starting position.

        @Query("offset") offset: Int
    ): PokemonList
}