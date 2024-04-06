package com.example.recipeapp.model.mealByName

data class SingleRecipe(
    val id: String,
    val image: String,
    val instructions: String,
    val ingredients: String,
    val name: String
)