package com.example.createyourfirstapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.createyourfirstapp.databinding.FragmentFourthBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit


class FourthFragment : Fragment() {

    val cities = listOf(City("Rome"), City("Berlin"), City("New York"), City("London"))

    val retrofit = Retrofit.Builder().baseUrl("https://api.github.com").build()
    val gitHubService = retrofit.create(GitHubService::class.java)


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
        viewLifecycleOwner.lifecycleScope.launch {
            val repo = gitHubService.listRepos("MiaSm97")
            Log.d("Fourth Fragment", "Repo size: $repo")
        }
    }


}

