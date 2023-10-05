package com.example.recipesapp.network

import com.example.recipesapp.model.Recipe


interface ApiService{
    @GET("recipes.json")
    suspend fun getRecipes():List<Recipe>
}
