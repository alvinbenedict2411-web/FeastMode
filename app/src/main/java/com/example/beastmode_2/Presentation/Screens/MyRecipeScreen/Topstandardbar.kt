package com.example.beastmode_2.Presentation.Screens.MyRecipeScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beastmode_2.ui.theme.orange

@Composable
fun Topstandardbar(icon: ImageVector,
                   title: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(imageVector = icon, contentDescription = null,
            tint= orange
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = title,
            color = orange,
            fontWeight = FontWeight.Black,
            fontSize = 20.sp)
    }
}