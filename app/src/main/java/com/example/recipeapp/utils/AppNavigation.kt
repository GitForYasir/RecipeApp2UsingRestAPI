package com.example.recipeapp.utils

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.viewModel.MainViewModel

@Composable
fun AppNavigation(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "homeScreen"){
        composable("homeScreen"){
            HomeScreen(mainViewModel, navController)
        }
        composable("detailScreen/{recipeId}"){backStackEntry->
            val recipeId = backStackEntry.arguments?.getString("recipeId")
            val singleRecipe = mainViewModel.filteredMeal.value?.meals?.find { it.idMeal == recipeId }
            singleRecipe?.let {
                DetailScreen(navController, singleRecipe, mainViewModel)
            }
        }
    }
}