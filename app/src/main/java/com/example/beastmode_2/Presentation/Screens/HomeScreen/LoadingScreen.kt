package com.example.beastmode_2.Presentation.Screens.HomeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.beastmode_2.ui.theme.orange

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        CircularProgressIndicator(
            color = orange,
        )
    }
}