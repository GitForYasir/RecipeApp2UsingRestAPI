package com.example.recipeapp.repository

import com.example.recipeapp.api.RecipeService
import com.example.recipeapp.model.allCategories.AllCategoriesList
import com.example.recipeapp.model.filterMealByCategory.FilterByCategoryList
import com.example.recipeapp.model.mealByName.MealList
import retrofit2.Response

class RecipeRepository(private val recipeService: RecipeService) {
    suspend fun getInstance(): MealList? {
        val result = recipeService.getRecipes("s")
        return result.body()
    }

    suspend fun getFilteredMeal(category: String): FilterByCategoryList?{
        val result = recipeService.getFilterMeal(category)
        return result.body()
    }


    suspend fun getAllCategories(): AllCategoriesList?{
        val result = recipeService.getAllCategories()
        return result.body()
    }
}

data class SingleCategory(val category: String = "Beef")