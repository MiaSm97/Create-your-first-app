package com.example.createyourfirstapp.pokemondtos

data class PokemonTypesItem(
    val pokemon_id: Int,
    val pokemon_name: String,
    val type: List<String>
)