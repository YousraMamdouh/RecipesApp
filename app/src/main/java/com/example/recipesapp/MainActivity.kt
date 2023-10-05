package com.example.recipesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipesapp.network.ApiHandler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiHandler().fetchData()
        setContentView(R.layout.activity_main)
    }
}