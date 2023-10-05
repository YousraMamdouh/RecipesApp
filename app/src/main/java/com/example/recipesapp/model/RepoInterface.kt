package com.example.recipesapp.model

interface RepoInterface {

        suspend fun getAllRecipes(): List<Recipe>


}