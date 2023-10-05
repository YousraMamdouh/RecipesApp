package com.example.recipesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.R
import com.example.recipesapp.model.Repository
import com.example.recipesapp.network.RecipesClient
import com.example.recipesapp.viewModel.HomeViewModel
import com.example.recipesapp.viewModel.HomeViewModelFactory

/**
 * Home View of the Recipes App responsible for displaying a list of recipes.
 */
class HomeView : AppCompatActivity() {
    // UI components
    private lateinit var recyclerView: RecyclerView
    private lateinit var factory: HomeViewModelFactory
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: RecipesAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        createViewModel()
        setUpRecyclerView()
        observeRecipesLiveData()
    }

    /**
     * Creates the ViewModel for the Home View and initializes it.
     */
    private fun createViewModel() {
        factory = HomeViewModelFactory(
            Repository.getInstance(
                RecipesClient.getInstance()
            )
        )
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

    }

    /**
     * Observes the LiveData for recipes and updates the RecyclerView when data changes.
     */
    private fun observeRecipesLiveData() {
        viewModel.recipes.observe(this) { recipes ->
            if (recipes != null) {
                adapter.submitList(recipes)
            }
        }
    }

    /**
     * Sets up the RecyclerView for displaying recipes.
     */
    private fun setUpRecyclerView() {
        layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        adapter = RecipesAdapter(this, ArrayList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
    }
}