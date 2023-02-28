package com.example.createyourfirstapp

import retrofit2.http.GET

interface PokemonService {
    @GET("/pokemon_types.json")
    suspend fun getDetails() : PokemonTypes
}