package com.example.beastmode_2.Presentation.Screens.CustomRecipeDetailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Egg
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import com.example.beastmode_2.Data.RoomDatabase.AddRecipe
import com.example.beastmode_2.Presentation.Screens.HomeScreen.LoadingScreen
import com.example.beastmode_2.Presentation.Screens.RecipeDetailsScreen.DetailsSection
import com.example.beastmode_2.Presentation.Screens.RecipeDetailsScreen.InfoChip
import com.example.beastmode_2.Presentation.Screens.RecipeDetailsScreen.StatItem
import com.example.beastmode_2.ui.theme.orange

@Composable
fun RecipeDetails(recipe: AddRecipe){
    Column(modifier = Modifier.fillMaxSize()
        .background(orange.copy(alpha=0.02f))
        .padding(16.dp)
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SubcomposeAsyncImage(model = recipe.image,
            contentDescription = null,
            error = {
                Box(modifier = Modifier.fillMaxSize()
                    .padding(10.dp),
                    contentAlignment = Alignment.Center){
                    Text(text = "Error Loading Image",
                        color = Color.Red,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)
                }
            },
            loading = {
                LoadingScreen()
            },
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Crop)
        //the content we write in {} is the content composable
        DetailsSection("Recipe Details",Icons.Default.RemoveRedEye) { //details section for content composable
            Text(text=recipe.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color=Color.DarkGray)
            Row(modifier=Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically) {
                InfoChip(label = recipe.cuisine,Icons.Default.Public)
                InfoChip(label = recipe.difficulty,Icons.Default.Star)
                InfoChip(label = recipe.mealType.firstOrNull()?:"Snacks",Icons.Default.Bolt)
            }
        }
        /*   Card(modifier=Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                border= CardDefaults.outlinedCardBorder()) {
                Column(modifier=Modifier.fillMaxWidth()
                    .padding(16.dp)){
                    Text(text=recipe.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color=Color.DarkGray)
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(modifier=Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        InfoChip(label = recipe.cuisine,Icons.Default.Public)
                        InfoChip(label = recipe.difficulty,Icons.Default.Star)
                        InfoChip(label = recipe.mealType.firstOrNull()?:"Snacks",Icons.Default.Bolt)
                    }
                }
            } */ //commented because we wanted reusable card, not just one, so did above and below one
        DetailsSection("At a Glance", Icons.Default.Timer) {
            Row(modifier=Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically) {
                StatItem(label = "Prep Time", value = "${recipe.prepTimeMinutes}m", Icons.Default.Schedule)
                VerticalDivider(modifier=Modifier.height(40.dp),
                    thickness = 3.dp,
                    color = Color.Gray.copy(0.2f))
                StatItem(label = "Cook Time", value = recipe.cookTimeMinutes.toString()+"m", Icons.Default.Whatshot)
                VerticalDivider(modifier=Modifier.height(40.dp),
                    thickness = 3.dp,
                    color = Color.Gray.copy(0.2f))
                StatItem(label = "Servings", value = recipe.servings.toString(), Icons.Default.Egg)
                VerticalDivider(modifier=Modifier.height(40.dp),
                    thickness = 3.dp,
                    color = Color.Gray.copy(0.2f))
                StatItem(label = "Calories", value = recipe.caloriesPerServing.toString(), Icons.Default.LocalFireDepartment)
            }
        }
        DetailsSection("Ingredients", Icons.Default.Restaurant) {
            recipe.ingredients.forEach {ingredient->
                Row(verticalAlignment = Alignment.CenterVertically){
                    Box(modifier = Modifier.padding(top = 1.dp)
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(orange))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text=ingredient,
                        color=Color.DarkGray.copy(alpha=0.85f))
                }
            }
        }
        DetailsSection("Instructions",Icons.Default.Info) {
            recipe.instructions.forEachIndexed { idx, instruction ->
                Row(verticalAlignment = Alignment.CenterVertically){
                    Box(modifier = Modifier.padding(top=5.dp)
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(orange),
                        contentAlignment = Alignment.Center){
                        Text(text = "${idx+1}",
                            fontSize = 12.sp,
                            color=Color.White,
                            fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text=instruction,
                        color=Color.DarkGray.copy(alpha=0.85f),
                        modifier=Modifier.padding(top=4.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}