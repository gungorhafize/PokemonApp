package com.hafize.pokemonapp.util
// Define a sealed class for representing different states (Success, Error, Loading) with associated data and optional error message.
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}
