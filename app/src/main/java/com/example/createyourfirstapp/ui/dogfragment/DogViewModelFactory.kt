package com.example.createyourfirstapp.ui.dogfragment

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.createyourfirstapp.ui.dogfragment.network.DogProvider

class DogViewModelFactory(
    private val dogProvider: DogProvider,
    private val preferences: SharedPreferences
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DogViewModel::class.java)) {
            return DogViewModel(dogProvider, preferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}