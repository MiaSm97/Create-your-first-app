package com.example.createyourfirstapp.ui.pokemonfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.createyourfirstapp.R
import com.example.createyourfirstapp.pokemondtos.PokemonTypesItem

class ViewHolder(view: View): RecyclerView.ViewHolder(view){

    val pokemonType: TextView
    init {
        pokemonType = view.findViewById(R.id.pokemon_type)
    }
}

class PokemonAdapter(private val pokemon: List<PokemonTypesItem>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val pokemonView = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item_list, parent, false)
        return ViewHolder(pokemonView)
    }

    override fun getItemCount(): Int {
        return pokemon.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pokemonType.text = pokemon[position].toString()
    }
}