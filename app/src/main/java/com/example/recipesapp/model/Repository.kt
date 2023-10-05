package com.example.recipesapp.model

import com.example.recipesapp.network.RemoteService
/**
 * Repository class responsible for managing data retrieval from various sources.
 *
 * @param remoteSource The remote data source implementing [RemoteService].
 */
class Repository(private var remoteSource: RemoteService):RepoInterface{
    /**
     * A companion object for creating a singleton instance of [Repository].
     */
    companion object{
        private var instance:Repository?=null
        /**
         * Gets or creates a singleton instance of [Repository].
         *
         * @param remoteSource The remote data source implementing [RemoteService].
         * @return The singleton instance of [Repository].
         */
        fun getInstance(
            remoteSource: RemoteService
        ):Repository{
            return instance?: synchronized(this){
                val temp=Repository(
                    remoteSource
                )
                instance=temp
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
        return remoteSource.getAllRecipes()
    }
}