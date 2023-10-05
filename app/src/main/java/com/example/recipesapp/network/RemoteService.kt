package com.example.recipesapp.network

import com.example.recipesapp.model.Recipe

interface RemoteService {
    suspend fun getAllRecipes():List<Recipe>
}