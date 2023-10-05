package com.example.recipesapp.model

import com.example.recipesapp.network.RemoteService

class Repository(private var remoteSource: RemoteService):RepoInterface{



    companion object{
        private var instance:Repository?=null
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


    override suspend fun getAllRecipes(): List<Recipe> {
        return remoteSource.getAllRecipes()
    }
}