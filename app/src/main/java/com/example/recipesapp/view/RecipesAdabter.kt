package com.example.recipesapp.view

import com.example.recipesapp.R
import com.example.recipesapp.model.Recipe

c
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


class RecipesAdabter(
    private var context: Context, private var array: ArrayList<Recipe>,
) : androidx.recyclerview.widget.ListAdapter<Recipe,RecipesAdabter.ViewHolder>(MyDiffUtil()) {

    var myContext:Context
    init {
        myContext=context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //    val context:FragmentA
        context=parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return ViewHolder(view)
    }


//    override fun getItemCount(): Int = products.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentStore:Recipe = getItem(position)
      //  Glide.with(context).load(currentStore.storeLogo).placeholder(R.drawable.placeholder_image).into(holder.StoreImage)

        holder.storeName.text = currentStore.name
        holder.storeDescription.text = currentStore.description




    }

    class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val storeName = itemView.findViewById<TextView>(R.id.)
        val storeDescription = itemView.findViewById<TextView>(R.id.)
      //  val StoreImage=itemView.findViewById<ImageView>(R.id.storeImage)



    }



}

class MyDiffUtil : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }

}