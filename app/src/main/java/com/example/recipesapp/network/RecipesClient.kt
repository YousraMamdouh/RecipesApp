package com.example.recipesapp.network

import com.example.recipesapp.model.Recipe

class RecipesClient: RemoteService {

    val apiService: ApiService by lazy {
        RetrofitHelper.retrofitInstance.create(ApiService::class.java)
    }


    companion object {
        private var instance: RecipesClient? = null
        fun getInstance(): RecipesClient {
            return instance ?: synchronized(this)
            {
                val temp = RecipesClient()
                instance = temp
                temp
            }
        }
    }

    override suspend fun getAllRecipes(): List<Recipe> {
        val response = apiService.getRecipes()
        return response
    }
}
