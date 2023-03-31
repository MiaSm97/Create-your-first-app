package com.example.createyourfirstapp.ui.pokemonfragment.network

import com.example.createyourfirstapp.pokemondtos.PokemonTypes
import retrofit2.http.GET

interface PokemonService {
    @GET("/pokemon_types.json")
    suspend fun getPokemonDetails() : PokemonTypes
}