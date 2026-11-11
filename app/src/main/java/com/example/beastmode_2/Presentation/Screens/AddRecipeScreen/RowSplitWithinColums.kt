package com.example.beastmode_2.Presentation.Screens.AddRecipeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beastmode_2.ui.theme.orange

@Composable
fun RowSplitWithinColums(title: String,
                         value1: String,
                         value2: String,
                         valueChange: (String) -> Unit,
                         modifier: Modifier = Modifier){
    Column(modifier=modifier){
        Text(text=title,
            color=Color.Black.copy(0.8f),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(value=value1,
            onValueChange = {valueChange(it)},
            placeholder = {Text(text=value2,
                color=Color.Gray,)},
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = orange,
                unfocusedBorderColor = Color.Black

            ),
            shape = RoundedCornerShape(10.dp),
            modifier=Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}