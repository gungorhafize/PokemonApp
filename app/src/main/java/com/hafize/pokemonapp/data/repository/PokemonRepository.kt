package com.hafize.pokemonapp.data.repository

import com.example.pokemon.data.remote.responses.Pokemon
import com.example.pokemon.data.remote.responses.PokemonList
import com.hafize.pokemonapp.util.Resource

interface PokemonRepository {
    // Define an interface method to fetch a list of Pokemon with the specified limit and offset.
    // It returns the result wrapped in a Resource object to handle various states.
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>

    suspend fun getPokemonDetail(name: String): Resource<Pokemon>

}