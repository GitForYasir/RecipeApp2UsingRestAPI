package com.example.recipeapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.recipeapp.model.filterMealByCategory.FilterMeal
import com.example.recipeapp.viewModel.MainViewModel

@Composable
fun DetailScreen(
    navController: NavHostController,
    singleRecipe: FilterMeal?,
    viewModel: MainViewModel
) {
    val recipeState by viewModel.recipes.observeAsState()
    var colorState by remember {
        mutableStateOf(false)
    }

    val recipeList by remember {
        mutableStateOf(recipeState?.meals ?: emptyList())
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            // Image with gradient overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f),
                contentAlignment = Alignment.TopCenter
            ) {
                AsyncImage(
                    model = singleRecipe!!.strMealThumb, // Assuming there's a field for image URL in your Recipe model
                    contentDescription = "image detail",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop // Adjust content scale as needed
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.White),
                                startY = 200f,
                                endY = 1200f // Adjust as needed for the gradient length
                            )
                        )
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)
                        .padding(horizontal = 6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .size(40.dp)
                            .background(Color(0xFFFA1457), shape = CircleShape)
                            .clickable {
                                navController.navigate("homeScreen")
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back move",
                            modifier = Modifier.align(Alignment.Center),
                            tint = Color.White
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .padding(5.dp)
                            .background(color = if (colorState) Color(0xFFFA1457) else Color.Black, shape = CircleShape)
                            .clickable {
                                colorState = !colorState
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "favorite",
                            modifier = Modifier.align(Alignment.Center).background(if (colorState) Color(0xFFFA1457) else Color.Transparent),
                            tint = Color.White
                        )

                    }
                }
            }

            // Text content
            Box(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .padding(bottom = 30.dp)
                    .weight(3f)
                    .background(Color.White, shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.TopStart
            ) {
                Column {
                    Text(
                        text = singleRecipe!!.strMeal,
                        fontWeight = Bold,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Instructions",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = singleRecipe.strMeal,
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.Gray
                    )

                    Text(
                        text = "Ingredients",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = singleRecipe.idMeal,
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.Gray
                    )
                }
            }
        }
    }
}


// paging  concept oin
/*
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerExample() {
    val items = (1..10).toList() // Example list of items

    HorizontalPager(
        state = rememberPagerState { items.size },
        modifier = Modifier.fillMaxSize()
    ) { page ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(color = if (page % 2 == 0) Color.Green else Color.Blue),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Page $page", fontSize = 24.sp, color = Color.White)
        }
    }
}*/
