package com.example.beastmode_2.Presentation.Screens.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beastmode_2.ui.theme.orange
import java.nio.file.WatchEvent

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Homeheader() {
    Row(modifier = Modifier.fillMaxWidth()
        .clip(RoundedCornerShape(20.dp))
        .background(orange.copy(alpha = 0.2f))
        .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier =Modifier.size(48.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(orange),
            contentAlignment = Alignment.Center
        ){
            Icon(imageVector = Icons.Default.RestaurantMenu,
                contentDescription = "Menu",
                tint = Color.White)
        }
        Spacer(modifier = Modifier.width(15.dp))
        Column() {
            Text(text="Hello Chef!",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text="Find your favorite recipe",
                color = Color.Black,
                fontWeight = FontWeight.Medium)
        }
    }
}