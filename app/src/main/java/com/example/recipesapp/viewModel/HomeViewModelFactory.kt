package com.example.recipesapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipesapp.model.RepoInterface

/**
 * Factory class for creating instances of [HomeViewModel].
 *
 * @param _repo An implementation of [RepoInterface] that provides data access.
 */
class HomeViewModelFactory(private val _repo: RepoInterface) : ViewModelProvider.Factory {
    /**
     * Creates and returns an instance of the specified ViewModel class.
     *
     * @param modelClass The class of the ViewModel to create.
     * @return An instance of the specified ViewModel class.
     * @throws IllegalArgumentException If the provided modelClass is not found.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModelFactory::class.java)) {
            HomeViewModel(_repo) as T
        } else {
            throw IllegalArgumentException("View class not found")
        }
    }
}