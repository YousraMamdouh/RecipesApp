package com.example.recipesapp.network

import com.example.recipesapp.model.Recipe

/**
 * A client class for fetching recipes from a remote data source.
 *
 * This class implements the [RemoteService] interface to provide a way to retrieve a list of recipes.
 */
class RecipesClient : RemoteService {

//    val apiService: ApiService by lazy {
//        RetrofitHelper.retrofitInstance.create(ApiService::class.java)
//    }
//
//
    /**
     * A companion object for creating a singleton instance of [RecipesClient].
     */
    companion object {
        private var instance: RecipesClient? = null

        /**
         * Gets or creates a singleton instance of [RecipesClient].
         *
         * @return The singleton instance of [RecipesClient].
         */
        fun getInstance(): RecipesClient {
            return instance ?: synchronized(this)
            {
                val temp = RecipesClient()
                instance = temp
                temp
            }
        }
    }

    /**
     * Retrieves a list of recipes from the remote data source.
     *
     * @return A list of [Recipe] objects representing recipes fetched from the remote source.
     */
    override suspend fun getAllRecipes(): List<Recipe> {
        return ApiHandler().fetchData()
    }

}


