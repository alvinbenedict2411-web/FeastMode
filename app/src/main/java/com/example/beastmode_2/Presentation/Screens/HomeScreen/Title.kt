package com.example.beastmode_2.Presentation.Screens.HomeScreen


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beastmode_2.ui.theme.orange

@Composable
fun Title(title: String,
          Icon: ImageVector,) {
    Row(modifier = Modifier.fillMaxWidth(),){
        Icon(imageVector = Icon,
            contentDescription = "Title",
            tint = orange.copy(alpha=0.9f),
            modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray)
    }
    }
