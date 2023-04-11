package com.example.createyourfirstapp

import android.app.Application
import android.content.SharedPreferences
import com.example.createyourfirstapp.ui.dogfragment.network.DogProvider
import com.example.createyourfirstapp.ui.dogfragment.DogViewModelFactory

class App : Application() {
    private val dogProvider = DogProvider()
    lateinit var dogViewModelFactory :DogViewModelFactory
    lateinit var preferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()

        preferences = getSharedPreferences("app", MODE_PRIVATE)
        dogViewModelFactory = DogViewModelFactory(dogProvider, preferences)
    }
}