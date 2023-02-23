package com.example.createyourfirstapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.createyourfirstapp.databinding.FragmentSecondBinding

class FifthFragment : Fragment() {
    private var _binding: FragmentFifthBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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
            findNavController().navigate(R.id.)
        }

        binding.buttonSecond.setOnClickListener {

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}