package com.example.beastmode_2.Presentation.Screens.RecipeDetailsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beastmode_2.ui.theme.orange

@Composable
fun StatItem(label:String,value: String,icon: ImageVector) {
    Column(
       horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = icon, contentDescription = null,
            tint = orange,
            modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text=value,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            fontSize = 16.sp)
        Text(text=label,
            color = Color.Gray,
            fontSize = 12.sp)
    }
}