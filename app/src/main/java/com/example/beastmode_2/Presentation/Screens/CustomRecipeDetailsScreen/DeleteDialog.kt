package com.example.beastmode_2.Presentation.Screens.CustomRecipeDetailsScreen


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.beastmode_2.ui.theme.orange

@Preview(showBackground = true, showSystemUi = true)
@Composable
 fun DeleteDialog(name: String,
                  ConfirmClick:()->Unit,
                  CancelClick:()->Unit){
    AlertDialog(onDismissRequest = {CancelClick()},
        confirmButton = {
                Button(onClick = {ConfirmClick()},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = orange,
                        contentColor =Color.White
                    ) ){
                    Text(text="Delete")
                }
        },
        dismissButton = {
            Button(onClick = {CancelClick()},
                colors = ButtonDefaults.buttonColors(
                    containerColor = orange,
                    contentColor =Color.White
                )) {
                Text(text="Cancel")
            }
        },
        title = {
            Text(text="Delete Recipe",
                color = Color.Black,
                fontWeight = FontWeight.Bold)
        },
        text = {
            Text(text="Are you sure you want to delete this ${name} recipe?",
                color = Color.Black)
        },
        shape = RoundedCornerShape(16.dp),
        containerColor = Color.White)
}