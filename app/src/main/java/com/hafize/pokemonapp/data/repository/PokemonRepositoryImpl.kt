package com.hafize.pokemonapp.data.repository

import com.example.pokemon.data.remote.responses.PokemonList
import com.hafize.pokemonapp.data.remote.PokeApi
import com.hafize.pokemonapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApi,
) : PokemonRepository {
    // Implementation of the PokemonRepository interface to fetch a list of Pokemon.
    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e : Exception){
            return Resource.Error("Unknown error occurred")

        }
        return Resource.Success(response)
    }

}