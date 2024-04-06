import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.recipeapp.model.mealByName.SingleRecipe
import com.example.recipeapp.repository.SingleCategory
import com.example.recipeapp.viewModel.MainViewModel


@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    val recipesState by viewModel.recipes.observeAsState()
    val filteredMealState by viewModel.filteredMeal.observeAsState()
    val allCategoryState by viewModel.allCategories.observeAsState()
    var categoryId by remember {
        mutableStateOf("1")
    }


    LaunchedEffect(Unit) {
        viewModel.loadFilteredMeal("Beef")
    }

    Box(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Categories",
                fontSize = 26.sp,
                color = Color(0xFFC74168),
                fontWeight = Bold,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 8.dp)
            )

            LazyRow(
                Modifier
                    .weight(2f)
                    .padding(top = 8.dp)
            ) {
                items(allCategoryState?.categories.orEmpty()) {    category ->
                    Card(
                        modifier = Modifier
                            .padding(5.dp),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .clickable {
                                    viewModel.loadFilteredMeal(category.strCategory)
                                    categoryId = category.idCategory
                                }
                                .background(
                                    color = if (categoryId == category.idCategory) Color(0xFFF39AB4) else Color(
                                        0xFFECECEC
                                    )
                                )
                        ) {
                            AsyncImage(
                                model = category.strCategoryThumb,
                                alpha = 0.95f,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                            )

                            Text(
                                text = category.strCategory,
                                color = Color(0xFFFA1457),
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(
                                        start = 8.dp
                                    ), // Adjust as needed for the text position
                                fontSize = 22.sp,
                                fontWeight = Bold
                            )
                        }
                    }
                }
            }

            LazyColumn(Modifier.weight(5f)) {
                items(filteredMealState?.meals.orEmpty()) { recipe ->
//                    if (recipe.strCategory == currentCategory) {
                    Card(
                        modifier = Modifier
                            .padding(top = 18.dp)
                            .padding(horizontal = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFF004A)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        val imageModel = recipe.strMealThumb
                        Box(modifier = Modifier.fillMaxSize()) {
                            AsyncImage(
                                model = imageModel,
                                alpha = 0.95f,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable {
                                        val singleRecipe = SingleRecipe(
                                            id = recipe.idMeal,
                                            image = recipe.strMealThumb,
                                            instructions = "instruction",
                                            ingredients = "ingredients",
                                            name = recipe.strMeal
                                        )
                                        navController.navigate("detailScreen/${singleRecipe.id}") {
                                            launchSingleTop = true
                                        }
                                    }
                            )

                            Text(
                                text = recipe.strMeal,
                                color = Color.White,
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(
                                        start = 16.dp,
                                        bottom = 8.dp
                                    ), // Adjust as needed for the text position
                                fontSize = 25.sp,
                                fontWeight = Bold
                            )
                        }
                    }

//                    }
                }
            }
        }
    }
}
