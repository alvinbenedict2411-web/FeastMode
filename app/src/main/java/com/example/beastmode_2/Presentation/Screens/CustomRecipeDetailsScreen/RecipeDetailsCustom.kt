package com.example.beastmode_2.Presentation.Screens.CustomRecipeDetailsScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.beastmode_2.Presentation.Navigation.Routes
import com.example.beastmode_2.Presentation.Screens.HomeScreen.LoadingScreen
import com.example.beastmode_2.Presentation.Viewmodels.ViewModels.CustomRecipeDetailsViewModel
import com.example.beastmode_2.ui.theme.orange
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RecipeDetailsCustom(
    id: Int,
    viewmodel: CustomRecipeDetailsViewModel,
    onbackclick: () -> Unit,
    onEditClick: () -> Unit,
    navController: NavHostController
) {
    LaunchedEffect(id) {
        viewmodel.getrecipethroughId(id)
    }
    val context = LocalContext.current
    val scope=rememberCoroutineScope()
    Scaffold(
        topBar = {
            MytopbarCustom(
                "Recipe Details",
                Icons.AutoMirrored.Filled.ArrowBack,
                Icons.Default.Edit,
                onBackClick = { onbackclick() },
                onclickEdit = { onEditClick() },
                icon3 = Icons.Default.Delete,
                onclickDelete = { viewmodel.ChangeAlertDialogVisibility(true) })
        }
    ) { innerpadding ->
        Box(
            modifier = Modifier.fillMaxSize()
                .background(orange.copy(alpha = 0.02f))
                .padding(innerpadding)
        ) {
            when {
                viewmodel.isloading -> LoadingScreen()
                viewmodel.errr_msg != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column() {
                            Text(
                                text = viewmodel.errr_msg!!,
                                color = Color.Red,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(
                                onClick = {
                                    viewmodel.getrecipethroughId(id)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = orange,
                                    contentColor = Color.White
                                )
                            ) {
                                Text(
                                    text = "Retry",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }

                viewmodel.recipe != null -> {
                    RecipeDetails(viewmodel.recipe!!)
                }
            }
            if (viewmodel.AlertDialogVisble) {
                viewmodel.recipe?.let {
                    DeleteDialog(
                        viewmodel.recipe!!.name,
                        ConfirmClick = {
                            viewmodel.deleteRecipe(viewmodel.recipe!!)
                            scope.launch {
                                delay(2000)
                                Toast.makeText(
                                    context,
                                    "Recipe Deleted Successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.popBackStack(Routes.MyRecipes, false)
                                viewmodel.ChangeAlertDialogVisibility(false)
                            }
                        },
                        CancelClick = { viewmodel.ChangeAlertDialogVisibility(false) })
                }
            }
        }
    }
}
