package com.example.createyourfirstapp

import android.app.Application
import com.example.createyourfirstapp.ui.dogfragment.network.DogProvider
import com.example.createyourfirstapp.ui.dogfragment.DogViewModelFactory

class App : Application() {
    private val dogProvider = DogProvider()
    val dogViewModelFactory = DogViewModelFactory(dogProvider)
}