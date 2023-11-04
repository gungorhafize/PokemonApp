package com.hafize.pokemonapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PokemonApp : Application(){
    override fun onCreate() {
        super.onCreate()
        // Initialize Timber for logging debug information.
        Timber.plant(Timber.DebugTree())
    }
}