package com.example.beastmode_2.Presentation.Screens.CustomRecipeDetailsScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MytopbarCustom(title:String,
             icon: ImageVector,
                   icon2: ImageVector,
                   icon3: ImageVector,
             onBackClick:()-> Unit,
                   onclickEdit: () -> Unit,
                   onclickDelete: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }){
                Icon(imageVector = icon, contentDescription = "Back", tint = Color.Black)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.DarkGray,
            navigationIconContentColor = Color.DarkGray
        ),
        actions = {
            IconButton(onClick = {
                onclickEdit()
            }){
                Icon(imageVector =icon2, contentDescription = "Edit", tint = Color.Black)
            }
            IconButton(onClick = {onclickDelete()}) {
                Icon(imageVector = icon3, contentDescription = "Delete", tint = Color.Black)
            }
        }

    )
}