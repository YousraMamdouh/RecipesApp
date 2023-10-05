package com.example.recipesapp.utilities

/**
 * Object containing network-related constants for the Recipes App.
 */
object Constants {
    //The base URL for the remote API from which recipes are fetched.
    const val BASE_URL =
        "https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/recipes.json"

    // A tag used for logging API responses , to know whether the data retrieved successfully or not .
    const val TAG = "Api Response"

    // message when no internet connection
    const val NO_INTERNET_MESSAGE = "No internet connection! check you connection and try again"

    // message when no api response or empty response
    const val API_RESPONSE_ERROR =
        "An error occurred while processing your request. Please try again later"
}