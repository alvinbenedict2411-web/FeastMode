package com.example.beastmode_2.Presentation.Screens.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuOpen
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.beastmode_2.Presentation.Navigation.BottomBar
import com.example.beastmode_2.Presentation.Navigation.Routes
import com.example.beastmode_2.Presentation.Viewmodels.ViewModels.HomeViewModel
import com.example.beastmode_2.ui.theme.orange

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen(
    viewmodel: HomeViewModel,
    onRecipeClick: (Int) -> Unit,
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Recipes",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray)
                }
            )
        },
        bottomBar = {
            BottomBar(isselected = "Home",navController)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = {navController.navigate(Routes.AddRecipe(id =null))},
                shape = RoundedCornerShape(16.dp),
                containerColor = orange,
                contentColor = Color.White,
                modifier = Modifier.padding(10.dp)) {
                Text(text = "+",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White)
            }
        }
    ) {innerpadding->
        Box(modifier = Modifier.fillMaxSize()
            .background(orange.copy(alpha=0.02f))
            .padding(innerpadding)){
            when{
                viewmodel.is_loading-> LoadingScreen()
                viewmodel.error_msg!=null->{
                    Box(modifier = Modifier.fillMaxSize()
                        .padding(16.dp),
                        contentAlignment = Alignment.Center){
                        Column(){
                            Text(text = viewmodel.error_msg?:"",
                                color = Color.Red,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(onClick = {
                                viewmodel.getrecipe()
                            },
                                modifier = Modifier.fillMaxWidth(),
                                shape=RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = orange,
                                    contentColor = Color.White
                                )) {
                                Text(text = "Retry",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White)
                            }
                        }
                    }
                }
                else->{
                    LazyVerticalGrid(columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(16.dp)) {
                        item(span = { GridItemSpan(currentLineSpan = maxCurrentLineSpan)}){
                            Homeheader()
                        }
                        if(viewmodel.allcategory.size>1){
                            item(span = { GridItemSpan(currentLineSpan = maxCurrentLineSpan)}){
                                CategoryCard(viewmodel.allcategory,
                                    viewmodel.selectedCategory,
                                    onchangeselected = {name->
                                        viewmodel.onselectedcategory(name)
                                    })
                            }
                        }
                        item(span = { GridItemSpan(currentLineSpan = maxCurrentLineSpan)}) {
                            Title(title=if(viewmodel.selectedCategory=="All") "All Recipes" else "${viewmodel.selectedCategory} Recipes",
                                Icons.Default.MenuOpen)
                        }
                        items(viewmodel.recipes,key={it.id}){recipe->
                            RecipeCard(recipe, onClick = {
                                onRecipeClick(recipe.id)
                            })
                        }
                    }
                }
            }
        }

    }
}