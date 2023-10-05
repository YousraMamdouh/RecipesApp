package com.example.recipesapp.network

import android.util.Log
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.utilities.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Handler class for making API calls and fetching data from a remote server.
 */
class ApiHandler {
    /**
     * Fetches data (recipes) from the specified API endpoint.
     *
     * @return A list of [Recipe] objects representing the fetched recipes.
     */
    suspend fun fetchData(): List<Recipe> {
        return withContext(Dispatchers.IO) {
            try {
                // Create an HTTP connection to the API endpoint.
                val connection =
                    URL(Constants.BASE_URL).openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.setRequestProperty("Content-Type", "application/json")
                connection.setRequestProperty("Accept", "application/json")
                connection.connectTimeout = 10000
                connection.readTimeout = 10000
                connection.doInput = true
                // Read the response from the server.
                val reader = InputStreamReader(connection.inputStream)
                val response = StringBuilder()
                val bufferedReader = BufferedReader(reader)
                bufferedReader.forEachLine {
                    response.append(it.trim())
                }
                // Log a success message and parse the response into a list of recipes.
                Log.d(Constants.TAG, "Success: Recipes retrieved successfully ")
                val gson = Gson()
                val recipeListType = object : TypeToken<List<Recipe>>() {}.type
                val recipes = gson.fromJson<List<Recipe>>(response.toString(), recipeListType)
                recipes
            } catch (e: Exception) {
                // Log an error message and return an empty list in case of an exception.
                Log.d(Constants.TAG, "Error: ${e.localizedMessage}")
                emptyList()
            }
        }
    }
}