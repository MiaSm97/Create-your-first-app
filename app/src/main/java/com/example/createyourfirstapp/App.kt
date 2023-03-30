package com.example.createyourfirstapp

import android.app.Application

class App : Application() {
    private val dogProvider = DogProvider()
    val mainViewModelFactory = MainViewModelFactory(dogProvider.setProvide())
}