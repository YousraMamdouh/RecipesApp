package com.example.recipesapp.model

class Repository(var remoteSource:RemoteService):RepoInterface{



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

    override suspend fun getAllStores(): List<Recipe> {
        //Log.d("Yousra", "Look: ${remoteSource.getAllStores()[0].storeName}")
        return remoteSource.getAllStores()
    }
}