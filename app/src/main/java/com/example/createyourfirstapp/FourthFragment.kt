package com.example.createyourfirstapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.createyourfirstapp.databinding.FragmentFourthBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FourthFragment : Fragment() {


    private var _binding: FragmentFourthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val authorizationInterceptor = AuthorizationInterceptor()
    val logging = HttpLoggingInterceptor()
    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging).addInterceptor(authorizationInterceptor)
        .build()
    val retrofit: Retrofit = Retrofit.Builder().client(client)
        .baseUrl("https://pokemon-go1.p.rapidapi.com")
        .addConverterFactory(GsonConverterFactory.create()).build()
    val apiService: PokemonService = retrofit.create(PokemonService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFourthBinding.inflate(inflater, container, false)

        logging.level = HttpLoggingInterceptor.Level.BODY

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showPokemon(PokemonTypes())
        retryCall()

    }



    fun retryCall() {

        viewLifecycleOwner.lifecycleScope.launch {
            try {

                val details = apiService.getDetails()

                showPokemon(details)

            } catch (e: Exception) {

                Snackbar.make(
                    requireActivity().findViewById(R.id.main_view),
                    "Error",
                    Snackbar.LENGTH_LONG
                ).setAction("retry") {
                    retryCall()
                }.show()

            }
        }

    }

    fun showPokemon(pokemon: PokemonTypes){

        val adapter = PokemonAdapter(pokemon)
        binding.pokemonTypeList.adapter = adapter
        binding.pokemonTypeList.layoutManager = LinearLayoutManager(context)

    }


}

