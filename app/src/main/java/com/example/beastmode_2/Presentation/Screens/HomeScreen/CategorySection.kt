package com.example.beastmode_2.Presentation.Screens.HomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.beastmode_2.ui.theme.orange
import java.nio.file.WatchEvent

@Composable
fun CategoryCard(
    categories:List<String>,
    selectedCategory:String,
    onchangeselected:(String)-> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Title("Categories", Icons.Default.Restaurant)
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)){
            items(categories){category->
                val isselected= selectedCategory==category
                FilterChip(
                    selected = isselected,
                    onClick = {onchangeselected(category)},
                    label={
                        Text(text = category,
                            fontWeight = if(isselected)FontWeight.Bold else FontWeight.Normal)
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = orange,
                        selectedLabelColor = Color.White,
                        labelColor = Color.DarkGray,
                        containerColor = Color.White
                    )
                )
            }
        }
    }
}