package com.example.beastmode_2.Presentation.Screens.MyRecipeScreen

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
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.beastmode_2.Presentation.Navigation.BottomBar
import com.example.beastmode_2.Presentation.Screens.HomeScreen.LoadingScreen
import com.example.beastmode_2.Presentation.Viewmodels.ViewModels.CustomRecipeViewModel
import com.example.beastmode_2.ui.theme.orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRecipeScreen(
    viewmodel: CustomRecipeViewModel,
    onRecipeClick: (Int) -> Unit,
    navController: NavHostController
) {
    LaunchedEffect(Unit){
        viewmodel.getdetails()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title={
                    Text(text = "Recipes",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black)
                }
            )
        },
        bottomBar = {
            BottomBar(isselected = "My Recipes", navController = navController)
        }
    ) {innerpadding->
        Box(modifier = Modifier.fillMaxSize()
            .background(orange.copy(alpha=0.02f))
            .padding(innerpadding)) {
            when{
                viewmodel.isloading-> LoadingScreen()
                viewmodel.errr_msg!=null->{
                    Box(modifier=Modifier.fillMaxSize()
                        .padding(16.dp),
                        contentAlignment = Alignment.Center){
                        Column() {
                            Text(text=viewmodel.errr_msg!!,
                                color = Color.Red,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(onClick = {
                                viewmodel.getdetails()
                            },
                                modifier = Modifier.fillMaxWidth(),
                                shape=RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = orange,
                                    contentColor = Color.White)) {
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
                        modifier=Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        item(span={ GridItemSpan(maxCurrentLineSpan)}){
                            Topstandardbar(Icons.Default.Bookmark,"Saved Recipes")
                        }
                        if(viewmodel.recipe.isEmpty()){
                            item(span={ GridItemSpan(maxCurrentLineSpan)}){
                                Box(modifier = Modifier.fillMaxWidth()
                                    .height(500.dp),
                                    contentAlignment = Alignment.Center) {
                                    Text(text = "No Recipes Found",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black)
                                }
                            }
                        }
                        else{
                            items(viewmodel.recipe){recipe->
                                Card(recipe = recipe,
                                    onClick = {
                                        onRecipeClick(recipe.id)
                                    })
                            }
                        }
                    }
                }
            }
        }
    }
}