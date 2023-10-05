package com.example.recipesapp.model

import java.io.Serializable

/**
 * Data class representing a recipe.
 *
 * @property calories The number of calories in the recipe.
 * @property carbos The amount of carbohydrates in the recipe.
 * @property difficulty The difficulty level of the recipe.
 * @property description A description of the recipe.
 * @property fats The amount of fats in the recipe.
 * @property headline The headline or title of the recipe.
 * @property id The unique identifier of the recipe.
 * @property image The URL or path to the image associated with the recipe.
 * @property name The name of the recipe.
 * @property proteins The amount of proteins in the recipe.
 * @property thumb The URL or path to a thumbnail image for the recipe.
 * @property time The time required to prepare the recipe.
 */
data class Recipe(
    val calories: String,
    val carbos: String,
    val difficulty: Int,
    val description: String,
    val fats: String,
    val headline: String,
    val id: String,
    val image: String,
    val name: String,
    val proteins: String,
    val thumb: String,
    val time: String,
) : Serializable
