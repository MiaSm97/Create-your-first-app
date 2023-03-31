package com.example.createyourfirstapp.ui.pokemonfragment

import com.example.createyourfirstapp.pokemondtos.PokemonTypes

sealed class GetPokemon{
    data class SetPokemonType(val name: String) : GetPokemon()
}

sealed class PokemonResults{
    data class Results(val pokemon: PokemonTypes) : PokemonResults()
    data class Errors(val errors: String) : PokemonResults()
}
