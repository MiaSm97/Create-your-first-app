package com.example.createyourfirstapp.ui.pokemonfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.createyourfirstapp.databinding.FragmentFourthBinding
import com.example.createyourfirstapp.pokemondtos.PokemonTypes
import okhttp3.logging.HttpLoggingInterceptor


class PokemonFragment : Fragment() {

    private lateinit var pokemonViewModel: PokemonViewModel
    private var _binding: FragmentFourthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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
        pokemonViewModel = ViewModelProvider(this)[PokemonViewModel::class.java]
        retryCall()
        pokemonViewModel.getPokemonApi(GetPokemon.SetPokemonType("pikachu"))

    }
    private fun retryCall() {
        pokemonViewModel.pokemon2.observe(viewLifecycleOwner) {
            when(it){
                is PokemonResults.Results -> showPokemon(it.pokemon)
                is PokemonResults.Errors -> {
                    Toast.makeText(
                        requireContext(),
                        "Error",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun showPokemon(pokemon: PokemonTypes){

        val adapter = PokemonAdapter(pokemon)
        binding.pokemonTypeList.adapter = adapter
        binding.pokemonTypeList.layoutManager = LinearLayoutManager(context)

    }


}

