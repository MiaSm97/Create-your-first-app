package com.example.createyourfirstapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.createyourfirstapp.databinding.FragmentFifthBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FifthFragment : Fragment() {

    private var _binding: FragmentFifthBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://dog.ceo")
        .addConverterFactory(GsonConverterFactory.create()).build()

    val apiService: DogService = retrofit.create(DogService::class.java)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        _binding = FragmentFifthBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.goToFourth.setOnClickListener {
            findNavController().navigate(R.id.action_FifthFragment_to_fourthFragment)
        }

        retryCall()
    }

    fun retryCall() {

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val details = apiService.getDetails()
                setDetails(details)

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

    fun setDetails(dog: Dog){
        binding.breed.text = getString(R.string.breed, dog.message.australian)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}