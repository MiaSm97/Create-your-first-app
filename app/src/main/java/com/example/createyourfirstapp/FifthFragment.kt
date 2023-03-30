package com.example.createyourfirstapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.createyourfirstapp.databinding.FragmentFifthBinding

class FifthFragment : Fragment() {

    private var _binding: FragmentFifthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFifthBinding.inflate(inflater, container, false)
        viewModel = (requireActivity().applicationContext as App).mainViewModelFactory.create(MainViewModel::class.java)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.goToFourth.setOnClickListener {
            findNavController().navigate(R.id.action_FifthFragment_to_fourthFragment)
        }

        retryCall()
        viewModel.dogApi()
    }

    private fun retryCall() {

        viewModel.dog2.observe(viewLifecycleOwner) {
            setDetails(it)
        }

        viewModel.error2.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                "Error",
                Toast.LENGTH_LONG
            ).show()
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