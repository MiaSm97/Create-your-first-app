package com.example.createyourfirstapp.ui.dogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.createyourfirstapp.App
import com.example.createyourfirstapp.R
import com.example.createyourfirstapp.databinding.FragmentFifthBinding
import com.example.createyourfirstapp.dogdtos.Dog
import kotlinx.coroutines.launch

class DogFragment : Fragment() {

    private var _binding: FragmentFifthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: DogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFifthBinding.inflate(inflater, container, false)
        viewModel = (requireActivity().applicationContext as App).dogViewModelFactory.create(
            DogViewModel::class.java)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.goToFourth.setOnClickListener {
            findNavController().navigate(R.id.action_FifthFragment_to_fourthFragment)
        }

        retryCall()
        viewModel.getDogApi(GetDogs.SetDogsBreed("australian"))
    }

    private fun retryCall() {
        lifecycleScope.launch {

            viewModel.dog.collect {
                when(it){
                    is DogResults.Results -> setDetails(it.dog)
                    is DogResults.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "Error",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    is DogResults.CheckPreferences -> {
                        Toast.makeText(
                            requireContext(),
                            "Dogs Breeds",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

    }

    private fun setDetails(dog: Dog) {
        binding.breed.text = getString(R.string.breed, dog.message.australian)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}