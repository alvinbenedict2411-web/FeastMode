package com.example.beastmode_2.Presentation.Screens.RecipeDetailsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun DetailsSection(title:String,
                   Icon: ImageVector,
                   content: @Composable () -> Unit) {
    Card(modifier=Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        border= CardDefaults.outlinedCardBorder()) {
        Column(modifier=Modifier.fillMaxWidth()
            .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)){
            Row(modifier=Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icon, contentDescription = null,
                    tint = orange,
                    modifier= Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color= Color.DarkGray)
            }
            Spacer(modifier = Modifier.height(1.dp))
            //did not use constant composable bcz we are changing the content composable
            content()
        }
    }
}