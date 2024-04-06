package com.example.recipeapp.api

import com.example.recipeapp.model.allCategories.AllCategoriesList
import com.example.recipeapp.model.filterMealByCategory.FilterByCategoryList
import com.example.recipeapp.model.mealByName.MealList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {
    @GET("search.php")
    suspend fun getRecipes(@Query("s") query: String): Response<MealList>

    @GET("filter.php")
    suspend fun getFilterMeal(@Query("c") query: String): Response<FilterByCategoryList>

    @GET("categories.php")
    suspend fun getAllCategories(): Response<AllCategoriesList>
}