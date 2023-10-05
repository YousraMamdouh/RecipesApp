package com.example.recipesapp.view

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.R
import com.example.recipesapp.model.Repository
import com.example.recipesapp.network.NetworkChecker
import com.example.recipesapp.network.RecipesClient
import com.example.recipesapp.viewModel.HomeViewModel
import com.example.recipesapp.viewModel.HomeViewModelFactory

/**
 * Home View of the Recipes App responsible for displaying a list of recipes.
 */
class HomeView : AppCompatActivity() {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkChecker = NetworkChecker(connectivityManager)

    // UI components
    private lateinit var recyclerView: RecyclerView
    private lateinit var factory: HomeViewModelFactory
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: RecipesAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var progressBar: ProgressBar
    private lateinit var noInternetRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        setMyProgressBarVisibility(true)
        createViewModel()
        setUpRecyclerView()
        // Initially, set the "No Internet" RecyclerView to be visible
        noInternetRecyclerView.visibility = RecyclerView.VISIBLE
        // Perform a network check and handle visibility based on network availability
        networkChecker.performAction {
            // When the network is available, observe recipes LiveData
            observeRecipesLiveData()
            // Hide the "No Internet" RecyclerView when the network is available
            noInternetRecyclerView.visibility = RecyclerView.GONE
        }
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
                setMyProgressBarVisibility(false)
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

    /**
     * Sets the visibility of the progress bar based on the provided boolean value.
     *
     * @param visibility True to make the progress bar visible, false to hide it.
     */
    private fun setMyProgressBarVisibility(visibility: Boolean) {
        if (visibility)
            progressBar.visibility = ProgressBar.VISIBLE
        else
            progressBar.visibility = ProgressBar.GONE
    }
}