package com.example.recipesapp.model

interface RepoInterface {

        suspend fun getAllStores(): List<Recipe>


}