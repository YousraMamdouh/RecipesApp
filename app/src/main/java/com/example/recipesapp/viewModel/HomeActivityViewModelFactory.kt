package com.example.recipesapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipesapp.model.RepoInterface

class HomeActivityViewModelFactory(private val _repo: RepoInterface): ViewModelProvider.Factory  {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if(modelClass.isAssignableFrom(HomeActivityViewModelFactory::class.java)){
                HomeActivityViewModelFactory(_repo) as T
            }else{
                throw IllegalArgumentException("View class not found")
            }


    }












}