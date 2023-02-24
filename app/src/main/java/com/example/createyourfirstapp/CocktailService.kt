package com.example.createyourfirstapp

import retrofit2.http.GET
import retrofit2.http.Path

interface CocktailService {
    @GET("api/json/v1/1/search.php?s=margarita")
    suspend fun listCocktail(@Path("name") name: String?): List<Cocktail>
}