package com.example.recipesapp.view

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.R
import com.example.recipesapp.model.Repository
import com.example.recipesapp.utilities.NetworkChecker
import com.example.recipesapp.network.RecipesClient
import com.example.recipesapp.utilities.Constants
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
    private lateinit var progressBar: ProgressBar
    private lateinit var noInternetCardView:CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        createViewModel()
        setMyProgressBarVisibility(true)
        setUpRecyclerView()
      //  noInternetCardView.visibility = CardView.VISIBLE

        // Initialize the ConnectivityManager to check network connectivity
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // Create an instance of NetworkChecker to perform network checks
        val networkChecker = NetworkChecker(connectivityManager)

        // Perform a network check and handle visibility based on network availability
        networkChecker.performAction {
            // Code to execute when there is a valid internet connection
            observeRecipesLiveData()
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
            if (!recipes.isNullOrEmpty()){
                adapter.submitList(recipes)
                setMyProgressBarVisibility(false)
        }
        else
            {
                Toast.makeText(this, Constants.API_RESPONSE_ERROR, Toast.LENGTH_LONG).show()
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
     * Initializes and sets up the views used in the activity.
     */
    private fun initializeViews() {
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        noInternetCardView = findViewById(R.id.internetProblemCardView)
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