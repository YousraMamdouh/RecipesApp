package com.example.recipesapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.RepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivityViewModel(private val _repo: RepoInterface): ViewModel() {

    private var _recipes: MutableLiveData<List<Recipe>> = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes
    init {
        getProductsFromAPI()
    }


    private fun getProductsFromAPI()
    {

        viewModelScope.launch(Dispatchers.IO) {
            _recipes.postValue(_repo.getAllRecipes())
        }
    }
}