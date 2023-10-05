package com.example.recipesapp.utilities

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.recipesapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


// Extension function to load an image from a URL using coroutines
suspend fun ImageView.loadImageAsync(url: String?) {
    try {
        val bitmap = withContext(Dispatchers.IO) {
            val inputStream: InputStream = URL(url).openStream()
            BitmapFactory.decodeStream(inputStream)
        }

        // Update the ImageView with the loaded image
        withContext(Dispatchers.Main) {
            setImageBitmap(bitmap)
        }
    } catch (e: IOException) {
        e.printStackTrace()
        // Handle loading failure here (e.g., set a placeholder image)
    }
}