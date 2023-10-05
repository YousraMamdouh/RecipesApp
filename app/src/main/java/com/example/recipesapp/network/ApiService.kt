package com.example.recipesapp.network

import com.example.recipesapp.model.Recipe
import retrofit2.http.GET

interface ApiService{
    @GET("recipes.json")
    suspend fun getRecipes():List<Recipe>
}
