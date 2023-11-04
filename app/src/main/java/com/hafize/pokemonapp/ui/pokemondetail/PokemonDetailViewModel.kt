package com.hafize.pokemonapp.ui.pokemondetail

import androidx.lifecycle.ViewModel
import com.example.pokemon.data.remote.responses.Pokemon
import com.hafize.pokemonapp.data.repository.PokemonRepository
import com.hafize.pokemonapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {

    suspend fun getPokemonDetail(name: String): Resource<Pokemon> {
        return repository.getPokemonDetail(name)
    }
}