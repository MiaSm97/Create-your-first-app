package com.example.createyourfirstapp

import retrofit2.http.GET

interface DogService {
    @GET("api/breeds/list/all")
    suspend fun getDetails() : Dog
}