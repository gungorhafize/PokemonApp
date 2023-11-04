package com.hafize.pokemonapp.di

import com.hafize.pokemonapp.data.remote.PokeApi
import com.hafize.pokemonapp.data.repository.PokemonRepository
import com.hafize.pokemonapp.data.repository.PokemonRepositoryImpl
import com.hafize.pokemonapp.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    // Provide an instance of PokemonRepository by injecting PokeApi
    fun providePokemonRepository(
        api: PokeApi,
    ): PokemonRepository {
        return PokemonRepositoryImpl(api)
    }
    @Singleton
    @Provides
    // Provide a Retrofit-based PokeApi instance with configuration.
    fun providePokeApi(): PokeApi {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient().newBuilder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .build()
            .create(PokeApi::class.java)
    }
}