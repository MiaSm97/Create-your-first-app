package com.example.createyourfirstapp.ui.dogfragment.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogProvider {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://dog.ceo")
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val apiService: DogService = retrofit.create(DogService::class.java)

    //fun setProvide() : DogService = apiService
    suspend fun setDetails() = apiService.getDetails()
}