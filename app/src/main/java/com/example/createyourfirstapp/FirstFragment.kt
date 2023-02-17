package com.example.createyourfirstapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.createyourfirstapp.databinding.FragmentFirstBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

private var _binding: FragmentFirstBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      _binding = FragmentFirstBinding.inflate(inflater, container, false)
      return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            val firstString = welcome()
            val secondString = nextScreen()
            printString(firstString, secondString)
        }
    }

    suspend fun welcome() : String {
        delay(1000)
        return "Welcome!"
    }
    suspend fun nextScreen() : String {
        delay(2000)
        return "Please, go to the next screen"
    }

    fun printString(value1 : String?, value2: String? ){
        if (value1 != null && value2 != null)
            Log.v("Main Activity", "$value1. $value2")
        binding.textviewFirst.text = "$value1 $value2"

    }


override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}