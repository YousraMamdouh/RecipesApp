package com.example.recipesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.model.Repository
import com.example.recipesapp.network.ApiHandler
import com.example.recipesapp.network.RecipesClient
import com.example.recipesapp.view.RecipesAdabter
import com.example.recipesapp.viewModel.HomeActivityViewModel
import com.example.recipesapp.viewModel.HomeActivityViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var factory: HomeActivityViewModelFactory
    lateinit var viewModel: HomeActivityViewModel
    lateinit var adapter: RecipesAdabter
    lateinit var layoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
   //     ApiHandler().fetchData()
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)

        factory= HomeActivityViewModelFactory(
            Repository.getInstance(
                RecipesClient.getInstance()

            ))
        viewModel= ViewModelProvider(this,factory).get(HomeActivityViewModel::class.java)

        setUpRecyclerView()

        viewModel.recipes.observe(this){ recipes->
            if(recipes!=null){
                adapter.submitList(recipes)
            }

        }

    }

    private fun setUpRecyclerView() {
        layoutManager=LinearLayoutManager(this)
        layoutManager.orientation=RecyclerView.VERTICAL
        adapter= this?.let { RecipesAdabter(it,ArrayList()) }!!
        recyclerView.adapter=adapter
        recyclerView.layoutManager=layoutManager
    }
}