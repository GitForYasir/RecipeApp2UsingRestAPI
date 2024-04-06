package com.example.recipeapp.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.model.allCategories.AllCategoriesList
import com.example.recipeapp.model.filterMealByCategory.FilterByCategoryList
import com.example.recipeapp.model.mealByName.MealList
import com.example.recipeapp.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: RecipeRepository): ViewModel() {

    private val _recipes = MutableLiveData<MealList?>()
    val recipes: LiveData<MealList?> = _recipes

    private val _filteredMeal = MutableLiveData<FilterByCategoryList?>()
    val filteredMeal: LiveData<FilterByCategoryList?> = _filteredMeal

    private val _allCategories = MutableLiveData<AllCategoriesList?>()
    val allCategories: LiveData<AllCategoriesList?> = _allCategories

    init {
        loadRecipes()
        loadAllCategories()
//        loadFilteredMeal(categoryName)
    }

    private fun loadRecipes(){
        viewModelScope.launch(Dispatchers.IO){
            val result = repository.getInstance()
            _recipes.postValue(result)
        }
    }

    fun loadFilteredMeal(category: String){
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getFilteredMeal(category)
            _filteredMeal.postValue(result)
        }
    }

    private fun loadAllCategories(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getAllCategories()
            _allCategories.postValue(result)
        }
    }
}