package com.example.beastmode_2.Presentation.Navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.beastmode_2.ui.theme.orange

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BottomBar(isselected: String, navController: NavHostController) {
    val items = listOf(
        Content("Home", Icons.Default.Home,Routes.Home),
        Content("My Recipes", Icons.Default.Bookmark,Routes.MyRecipes)
    )
    NavigationBar(modifier=Modifier.fillMaxWidth(),
        containerColor = Color.White) {
        items.forEach { item ->
            NavigationBarItem(selected = isselected==item.title,
                onClick = {
                    if (item.title == "Home") {
                        // Go back to the existing Home screen
                        navController.popBackStack(
                            Routes.Home,
                            false
                        )
                    } else {
                        navController.navigate(item.Navigation) {

                            popUpTo(Routes.Home) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    } },
                icon={ Icon(item.icon, contentDescription = item.title)},
                label={
                    Text(text = item.title,
                        color=Color.Black,
                        fontSize=12.sp,
                        fontWeight = FontWeight.Bold)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.Black,
                    indicatorColor = orange,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                ))
        }
    }
}
data class Content(val title: String, val icon: ImageVector,val Navigation: Routes)