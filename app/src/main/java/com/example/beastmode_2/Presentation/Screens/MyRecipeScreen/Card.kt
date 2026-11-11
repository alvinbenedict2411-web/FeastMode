package com.example.beastmode_2.Presentation.Screens.MyRecipeScreen

import com.example.beastmode_2.Data.RoomDatabase.AddRecipe
import com.example.beastmode_2.Presentation.Screens.HomeScreen.LoadingScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.example.beastmode_2.Data.Remote.DTO.RecipeDTO
import com.example.beastmode_2.ui.theme.orange
@Composable
fun Card(
    recipe: AddRecipe,
    onClick: () -> Unit
){
    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable(onClick={onClick()}),
        shape= RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            focusedElevation = 20.dp,
            hoveredElevation = 25.dp,
            draggedElevation = 30.dp
        ),
        colors = CardDefaults.cardColors(
            contentColor = Color.White
        ),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            SubcomposeAsyncImage(model = recipe.image,
                contentDescription = recipe.name,
                modifier=Modifier.fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.Crop,
                loading = {
                    Box(modifier= Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center){
                        LoadingScreen()
                    }
                },
                error = {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center){
                        Text(
                            text = "Image unavailable",
                            color = Color.Red,
                            fontSize = 14.sp
                        )
                    }
                })
            Spacer(modifier = Modifier.height(5.dp))
            Column(modifier=Modifier.fillMaxWidth()
                .padding(12.dp)) {
                Text(text=recipe.name,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    color=Color.DarkGray)
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier=Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text=recipe.cuisine,
                        fontSize = 12.sp,
                        color=Color.DarkGray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.weight(1f))
                    Box(modifier= Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(orange.copy(alpha = 0.1f),RoundedCornerShape(10.dp))
                        .padding(horizontal = 8.dp,vertical = 4.dp)){
                        Text(text=recipe.difficulty,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color=orange)
                    }
                }
            }
        }
    }
}
