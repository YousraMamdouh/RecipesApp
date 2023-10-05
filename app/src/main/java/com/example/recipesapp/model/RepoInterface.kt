package com.example.recipesapp.model
/**
 * Interface defining the contract for retrieving a list of recipes.
 */
interface RepoInterface {
        /**
         * Retrieves a list of recipes.
         *
         * @return A list of [Recipe] objects representing recipes.
         */
        suspend fun getAllRecipes(): List<Recipe>
}