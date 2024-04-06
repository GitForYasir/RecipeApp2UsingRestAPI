package com.example.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.api.RecipeService
import com.example.recipeapp.api.RetrofitHelper
import com.example.recipeapp.repository.RecipeRepository
import com.example.recipeapp.ui.theme.RecipeAppTheme
import com.example.recipeapp.viewModel.MainViewModel
import com.example.recipeapp.viewModel.MainViewModelFactory
import com.example.recipeapp.utils.AppNavigation
import com.example.recipeapp.viewModel.SecondViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private val secondViewModel by viewModels<SecondViewModel>()

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                !secondViewModel.isReady.value
            }
        }

        val recipeService = RetrofitHelper.getInstance().create(RecipeService::class.java)
        val repository = RecipeRepository(recipeService)
        mainViewModel = ViewModelProvider(this,
            MainViewModelFactory(repository))[MainViewModel::class.java]

        setContent {
            RecipeAppTheme {
                AppNavigation(mainViewModel = mainViewModel)
            }
        }
    }
}