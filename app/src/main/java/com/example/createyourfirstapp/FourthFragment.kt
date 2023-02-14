package com.example.createyourfirstapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.createyourfirstapp.databinding.FragmentFourthBinding


/**
 * A simple [Fragment] subclass as the fourth destination in the navigation.
 */
class FourthFragment : Fragment() {

    val cities = listOf(City("Rome"), City("Berlin"), City("New York"), City("London"))


    private var _binding: FragmentFourthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CityAdapter(cities)
        binding.cityList.adapter = adapter
        binding.cityList.layoutManager = LinearLayoutManager(context)
    }


}

