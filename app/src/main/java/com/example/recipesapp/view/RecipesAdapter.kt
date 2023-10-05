package com.example.recipesapp.view

import com.example.recipesapp.R
import com.example.recipesapp.model.Recipe
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter class for displaying a list of recipes in a RecyclerView.
 *
 * @param context The context of the parent activity.
 * @param array The list of recipes to display.
 */

class RecipesAdapter(
    private var context: Context, private var array: ArrayList<Recipe>,
) : androidx.recyclerview.widget.ListAdapter<Recipe, RecipesAdapter.ViewHolder>(MyDiffUtil()) {

    var myContext: Context

    init {
        myContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //    val context:FragmentA
        context = parent.context
        //Inflate the layout for each item in the RecyclerView.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return ViewHolder(view)
    }


//    override fun getItemCount(): Int = products.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind data to the views of each RecyclerView item.
        val currentRecipe: Recipe = getItem(position)
        //  Glide.with(context).load(currentStore.storeLogo).placeholder(R.drawable.placeholder_image).into(holder.StoreImage)
        holder.recipeName.text = currentRecipe.name
        holder.recipeDescription.text = currentRecipe.description
        holder.recipeImage.setImageURI(Uri.parse(currentRecipe.thumb))


    }

    /**
     * ViewHolder class for caching views to improve RecyclerView performance.
     *
     * @param itemView The view for a single item in the RecyclerView.
     */
    class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeName: TextView = itemView.findViewById(R.id.recipeName)
        val recipeDescription: TextView = itemView.findViewById(R.id.recipeDescription)
        val recipeImage:ImageView = itemView.findViewById(R.id.recipeImage)
        //  val StoreImage=itemView.findViewById<ImageView>(R.id.storeImage)

    }
}

/**
 * DiffUtil.ItemCallback implementation for efficient RecyclerView updates.
 */
class MyDiffUtil : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        // Check if the items are the same by comparing their references.
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        // Check if the contents of the items are the same by comparing their content.
        return oldItem == newItem
    }

}