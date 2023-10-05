package com.example.recipesapp.network
/**
 * Object containing network-related constants for the Recipes App.
 */
object NetworkConstants {
        /**
         * The base URL for the remote API from which recipes are fetched.
         */
        const val BASE_URL = "https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/recipes.json"
        /**
         * A tag used for logging API responses , to know whether the data retrieved successfully or not .
         */
        const val TAG = "Api Response"
}