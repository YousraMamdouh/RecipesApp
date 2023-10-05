package com.example.recipesapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.RepoInterface
import kotlinx.coroutines.Dispatchers

class HomeActivityViewModel(private val _repo: RepoInterface): ViewModel() {



    private var _stores: MutableLiveData<List<Recipe>> = MutableLiveData<List<Recipe>>()
    val products: LiveData<List<Recipe>> = _stores



    init {
        getProductsFromAPI()
    }


    private fun getProductsFromAPI()
    {
        viewModelScope.launch(Dispatchers.IO) {
            _stores.postValue(_repo.getAllRecipes())
        }
    }
}