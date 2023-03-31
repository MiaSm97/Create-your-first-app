package com.example.createyourfirstapp.ui.pokemonfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.createyourfirstapp.pokemondtos.PokemonTypes
import com.example.createyourfirstapp.ui.pokemonfragment.network.AuthorizationInterceptor
import com.example.createyourfirstapp.ui.pokemonfragment.network.PokemonService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonViewModel : ViewModel() {
    private var pokemon = MutableLiveData<PokemonResults>()
    val pokemon2 : LiveData<PokemonResults>
        get() = pokemon


    private fun pokemonApi(){
        CoroutineScope(Dispatchers.Main).launch {
            try{
                pokemon.value = PokemonResults.Results(pokemonService.getPokemonDetails())
            }catch (e: Exception){
                pokemon.value = e.localizedMessage?.let { PokemonResults.Errors(it) }
            }
        }
    }

    fun getPokemonApi(setPokemonType: GetPokemon){
        when(setPokemonType){
            is GetPokemon.SetPokemonType -> pokemonApi()
        }
    }
}

val authorizationInterceptor = AuthorizationInterceptor()
val logging = HttpLoggingInterceptor()
val client: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(logging).addInterceptor(authorizationInterceptor)
    .build()
val retrofit: Retrofit = Retrofit.Builder().client(client)
    .baseUrl("https://pokemon-go1.p.rapidapi.com")
    .addConverterFactory(GsonConverterFactory.create()).build()
val pokemonService: PokemonService = retrofit.create(PokemonService::class.java)

