package com.example.recipesapp.network

import android.util.Log
import com.example.recipesapp.model.Recipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ApiHandler {
    val TAG = "domy"

   fun fetchData(){
        suspend {
            val connection = URL(NetworkConstants.BASE_URL).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Content-Type","application/json")
            connection.setRequestProperty("Accept","application/json")
            connection.connectTimeout  = 10000
            connection.readTimeout = 10000
            connection.doInput = true

            try {
                val reader = InputStreamReader(connection.inputStream)
                reader.use {
                        input ->
                    val response = StringBuilder()
                    val bufferedReader = BufferedReader(input)
                    bufferedReader.forEachLine {
                        response.append(it.trim())
                    }
                    Log.d(TAG,"In_Success")

                    val gson = Gson()
                    // val arrayOfRecipes = gson.fromJson(response.toString(), Recipe::class.java)
                    // val recipes = arrayOfRecipes.recipes
                    val recipeListType = object : TypeToken<List<Recipe>>() {}.type
                    val recipes = gson.fromJson<List<Recipe>>(response.toString(), recipeListType)
                    for (recipe in recipes) {
                        Log.d(TAG, "Recipe ID: ${recipe.id}, Name: ${recipe.name}")
                    }
                }
            }catch (e:Exception)
            {
                Log.d(TAG,"In_Error ${e.localizedMessage}")
            }
        }

    }
}