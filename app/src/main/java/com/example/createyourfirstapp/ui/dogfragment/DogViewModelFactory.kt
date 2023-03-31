package com.example.createyourfirstapp.ui.dogfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.createyourfirstapp.ui.dogfragment.network.DogProvider

class DogViewModelFactory(private val dogProvider: DogProvider) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DogViewModel::class.java)) {
            return DogViewModel(dogProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}