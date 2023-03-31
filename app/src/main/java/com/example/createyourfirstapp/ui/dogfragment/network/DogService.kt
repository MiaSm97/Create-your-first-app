package com.example.createyourfirstapp.ui.dogfragment.network

import com.example.createyourfirstapp.dogdtos.Dog
import retrofit2.http.GET

interface DogService {
    @GET("api/breeds/list/all")
    suspend fun getDetails() : Dog
}