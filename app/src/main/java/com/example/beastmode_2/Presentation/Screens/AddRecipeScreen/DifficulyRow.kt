package com.example.beastmode_2.Presentation.Screens.AddRecipeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.beastmode_2.ui.theme.orange

@Composable
fun DifficulyRow(difficulyList: List<String>,
                 selectedDifficulty: String,
                 onDifficultySelected: (String) -> Unit,) {
    Row(modifier=Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp)) {
        difficulyList.forEach { difficuly->
            val isSelected = difficuly == selectedDifficulty
            FilterChip(
                selected = isSelected,
                onClick = {
                    onDifficultySelected(difficuly)
                },
                label = {
                    Text(text = difficuly,
                        color = if (isSelected) Color.White else Color.Black)
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = orange,
                    selectedLabelColor = Color.White,
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(16.dp)
            )
        }
    }
}