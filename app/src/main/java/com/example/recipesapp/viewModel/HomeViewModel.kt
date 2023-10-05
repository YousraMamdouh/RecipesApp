package com.example.recipesapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.RepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel class for the home activity, responsible for managing recipe data and interactions.
 *
 * @param _repo An implementation of [RepoInterface] that provides data access.
 */
class HomeViewModel(private val _repo: RepoInterface) : ViewModel() {
    // LiveData to hold a list of recipes retrieved from the repository.
    private var _recipes: MutableLiveData<List<Recipe>> = MutableLiveData<List<Recipe>>()

    // Expose the recipes as LiveData to observe changes from the UI.
    val recipes: LiveData<List<Recipe>> = _recipes
    /**
     * Initializes the ViewModel and fetches recipes from the API.
     */
    init {
        getProductsFromAPI()
    }
    /**
     * Fetches recipes from the API using a coroutine on the IO dispatcher.
     * Updates the [_recipes] LiveData with the fetched data.
     */
    private fun getProductsFromAPI() {
        viewModelScope.launch(Dispatchers.IO) {
            _recipes.postValue(_repo.getAllRecipes())
        }
    }
}