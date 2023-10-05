package com.example.recipesapp.network

import com.example.recipesapp.model.Recipe
/**
 * Interface defining the contract for retrieving a list of recipes from a remote data source.
 */
interface RemoteService {
    /**
     * Retrieves a list of recipes from the remote data source.
     *
     * @return A list of [Recipe] objects representing recipes fetched from the remote source.
     */
    suspend fun getAllRecipes():List<Recipe>
}