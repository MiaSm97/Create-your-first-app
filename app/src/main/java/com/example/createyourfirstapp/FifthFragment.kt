package com.example.createyourfirstapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.createyourfirstapp.databinding.FragmentFifthBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class FifthFragment : Fragment() {
    private var _binding: FragmentFifthBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var currentNumber = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFifthBinding.inflate(inflater, container, false)
        return binding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.goToForth.setOnClickListener {
            findNavController().navigate(R.id.action_FifthFragment_to_FirstFragment)
        }

        binding.tap.setOnClickListener {
            runBlocking {
                valueFirst()
            }
        }
    }

    suspend fun valueFirst() {
        delay(2000)
        val number = binding.hintNum.text.toString().toInt()
        val result = number + 1 + currentNumber
        currentNumber++
        binding.helloWorld.text = result.toString()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}