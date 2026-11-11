package com.example.beastmode_2.Presentation.Screens.RecipeDetailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beastmode_2.Presentation.Screens.HomeScreen.LoadingScreen
import com.example.beastmode_2.Presentation.Viewmodels.ViewModels.RecipeDetailsViewModel
import com.example.beastmode_2.ui.theme.orange

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecipeDetailsScreen(
    id: Int,
    onBackClick: () -> Unit,
    viewmodel: RecipeDetailsViewModel) {

    LaunchedEffect(id) {
        viewmodel.getrecipe(id)
    }
    Scaffold(
        topBar = {
            Mytopbar("Recipe Details",Icons.AutoMirrored.Filled.ArrowBack,onBackClick={onBackClick()})
        }
    ) {innerpadding->
        Box(modifier = Modifier.fillMaxSize()
            .background(orange.copy(alpha=0.02f))
            .padding(innerpadding)
        ) {
            when{
                viewmodel.isloading->LoadingScreen()
                viewmodel.error_msg!=null->{
                    Box(modifier = Modifier.fillMaxSize()
                        .padding(16.dp),
                        contentAlignment = Alignment.Center) {
                        Column() {
                            Text(text=viewmodel.error_msg!!,
                                color=Color.Red,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(onClick ={
                                viewmodel.getrecipe(id)
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
                viewmodel.recipe!=null->{
                    RecipeDetailsContent(viewmodel.recipe!!)
                }

            }
        }
    }
}