package com.example.recipesapp.network

import android.util.Log
import com.example.recipesapp.model.Recipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ApiHandler {

    suspend fun fetchData(): List<Recipe> {
        return withContext(Dispatchers.IO) {
            try {
                val connection = URL(NetworkConstants.BASE_URL).openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.setRequestProperty("Content-Type", "application/json")
                connection.setRequestProperty("Accept", "application/json")
                connection.connectTimeout = 10000
                connection.readTimeout = 10000
                connection.doInput = true
                val reader = InputStreamReader(connection.inputStream)
                val response = StringBuilder()
                val bufferedReader = BufferedReader(reader)
                bufferedReader.forEachLine {
                    response.append(it.trim())
                }

                Log.d(NetworkConstants.TAG, "In_Success")
                val gson = Gson()
                val recipeListType = object : TypeToken<List<Recipe>>() {}.type
                val recipes = gson.fromJson<List<Recipe>>(response.toString(), recipeListType)
                recipes
            } catch (e: Exception) {
                Log.d(NetworkConstants.TAG, "In_Error ${e.localizedMessage}")
                emptyList()
            }
        }
    }
}