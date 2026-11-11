package com.example.beastmode_2.Presentation.Screens.AddRecipeScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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

import com.example.beastmode_2.Data.RoomDatabase.AddRecipe
import com.example.beastmode_2.Presentation.Screens.HomeScreen.LoadingScreen
import com.example.beastmode_2.Presentation.Screens.RecipeDetailsScreen.Mytopbar
import com.example.beastmode_2.Presentation.Viewmodels.ViewModels.AddRecipeViewModel
import com.example.beastmode_2.ui.theme.orange
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AddRecipeScreen(
    id: Int? = null,
    viewmodel: AddRecipeViewModel,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    onClickRetry: () -> Unit,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    if (id != null) {
        LaunchedEffect(id) {
            viewmodel.EditRecipeExists(id)
        }
        //did this bcz the below onwe bcz to get deatils abt recipe it was courutine fun sooit was delay so itfilled null value ,so we had to do launhed effect and ensue that ?. with this only when value exits then do that update thing
        LaunchedEffect(viewmodel.recipe) {
            viewmodel.recipe?.let {recipe ->
                viewmodel.updatetolatestdataoneditclick(
                    recipe.name,
                    recipe.cuisine,
                    recipe.difficulty,
                    recipe.image,
                    recipe.prepTimeMinutes,
                    recipe.cookTimeMinutes,
                    recipe.servings,
                    recipe.caloriesPerServing,
                    recipe.instructions,
                    recipe.ingredients, viewmodel.recipe!!.tags,
                    recipe.mealType,
                    recipe.id
                )
            }
        }
    }
    Scaffold(topBar = {
        Mytopbar("Add Recipe", Icons.AutoMirrored.Filled.ArrowBack, onBackClick = { onBackClick()
        viewmodel.reset()})
    }) { innerpadding ->
        Box(
            modifier = Modifier.fillMaxSize()
                .background(orange.copy(alpha = 0.02f))
                .padding(innerpadding)
        ) {
            when {
                viewmodel.isloading-> LoadingScreen()
                viewmodel.error_msg != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column() {
                            Text(
                                text = viewmodel.error_msg!!,
                                color = Color.Red,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(
                                onClick = {
                                    onClickRetry()
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = orange,
                                ),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 10.dp
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

                viewmodel.recipe == null -> {
                    Column(
                        modifier = Modifier.fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "Recipe Name",
                            color = Color.Black.copy(0.8f),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        OutlinedTextField(
                            value = viewmodel.name,
                            onValueChange = { viewmodel.nameChange(it) },
                            placeholder = {
                                Text(
                                    text = "Eg.Chicken Curry",
                                    color = Color.Gray,
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = orange,
                                unfocusedBorderColor = Color.Black
                            ),
                            shape = RoundedCornerShape(10.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Cuisine",
                            color = Color.Black.copy(0.8f),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        OutlinedTextField(
                            value = viewmodel.cuisine,
                            onValueChange = { viewmodel.cusineChange(it) },
                            placeholder = {
                                Text(
                                    text = "Eg.Indian",
                                    color = Color.Gray,
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = orange,
                                unfocusedBorderColor = Color.Black
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Difficulty",
                            color = Color.Black.copy(0.8f),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        DifficulyRow(
                            viewmodel.allDifficulty,
                            viewmodel.selectedDifficulty!!,
                            onDifficultySelected = { difficulty ->
                                viewmodel.DiffcultyChange(difficulty)
                            })
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Image URL",
                            color = Color.Black.copy(0.8f),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        OutlinedTextField(
                            value = viewmodel.image,
                            onValueChange = { viewmodel.imageChange(it) },
                            placeholder = {
                                Text(
                                    text = "https://",
                                    color = Color.Gray,
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = orange,
                                unfocusedBorderColor = Color.Black

                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            RowSplitWithinColums(
                                "Prep Time",
                                viewmodel.prepTimeMinutes.toString(),
                                "Eg.10",
                                valueChange = { value ->
                                    viewmodel.prepTimeMinutesChange(value.toIntOrNull() ?: 0)
                                },
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            RowSplitWithinColums(
                                "Cook Time",
                                viewmodel.cookTimeMinutes.toString(),
                                "Eg.20",
                                valueChange = { value ->
                                    viewmodel.cookTimeMinutesChange(value.toIntOrNull() ?: 0)
                                },
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            RowSplitWithinColums(
                                "Servings",
                                viewmodel.servings.toString(),
                                "Eg.2",
                                valueChange = { value ->
                                    viewmodel.servingsChange(value.toIntOrNull() ?: 0)
                                },
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            RowSplitWithinColums(
                                "Calories Per Serving",
                                viewmodel.caloriesPerServing.toString(),
                                "Eg.100",
                                valueChange = { value ->
                                    viewmodel.caloriesPerServingChange(value.toIntOrNull() ?: 0)
                                },
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Instructions",
                            color = Color.Black.copy(0.8f),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        viewmodel.instructions.forEachIndexed { index, instruction ->
                            Row(modifier = Modifier.fillMaxWidth()) {
                                OutlinedTextField(
                                    value = instruction,
                                    onValueChange = { viewmodel.updateInstruction(index, it) },
                                    placeholder = {
                                        Text(
                                            text = "Instruction ${index + 1}",
                                            color = Color.Gray,
                                        )
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = orange,
                                        unfocusedBorderColor = Color.Black),
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                IconButton(
                                    onClick = { viewmodel.removeInstruction(index) },
                                    modifier = Modifier.background(orange.copy(alpha = 0.02f))
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Cancel,
                                        contentDescription = "Remove",
                                        tint = Color.Black,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                        }
                        TextButton(onClick = { viewmodel.addInstruction() }) {
                            Text(
                                text = "+ Add",
                                color = orange,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Ingredients",
                            color = Color.Black.copy(alpha = 0.8f),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        viewmodel.ingredients.forEachIndexed { index, ingredient ->
                            Row(modifier = Modifier.fillMaxWidth()) {
                                OutlinedTextField(
                                    value = ingredient,
                                    onValueChange = { viewmodel.updateIngredient(index, it) },
                                    placeholder = {
                                        Text(
                                            text = "Ingredient ${index + 1}",
                                        )
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = orange,
                                        unfocusedBorderColor = Color.Black

                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.weight(1f))

                                Spacer(modifier = Modifier.width(5.dp))
                                IconButton(
                                    onClick = { viewmodel.removeIngredient(index) },
                                    modifier = Modifier.background(orange.copy(alpha = 0.02f))){
                                    Icon(
                                        imageVector = Icons.Default.Cancel,
                                        contentDescription = "Remove",
                                        tint = Color.Black,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                        }
                        TextButton(onClick = { viewmodel.addIngredient() }) {
                            Text(
                                text = "+ Add",
                                color = orange,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )}
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text="Meal Type",
                            color = Color.Black.copy(alpha = 0.8f),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(5.dp))
                        viewmodel.mealType.forEachIndexed { index, mealType ->
                            Row(modifier = Modifier.fillMaxWidth()) {
                                OutlinedTextField(
                                    value = mealType,
                                    onValueChange = { viewmodel.updateMealType(index, it) },
                                    placeholder = {
                                        Text(
                                            text = "Meal Type ${index + 1}",
                                        )},
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = orange,
                                        unfocusedBorderColor = Color.Black

                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.weight(1f))
                                Spacer(modifier = Modifier.width(5.dp))
                                IconButton(
                                    onClick = { viewmodel.removeMealType(index) },
                                    modifier = Modifier.background(orange.copy(alpha = 0.02f))){
                                    Icon(
                                        imageVector = Icons.Default.Cancel,
                                        contentDescription = "Remove",
                                        tint = Color.Black,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                        }
                        TextButton(onClick = { viewmodel.addMealType() }) {
                            Text(
                                text = "+ Add",
                                color = orange,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )}
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text="Tags",
                            color = Color.Black.copy(alpha = 0.8f),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(5.dp))
                        viewmodel.tags.forEachIndexed { index, tag ->
                            Row(modifier = Modifier.fillMaxWidth()) {
                                OutlinedTextField(
                                    value = tag,
                                    onValueChange = { viewmodel.updateTag(index, it) },
                                    placeholder = {
                                        Text(
                                            text = "Tag ${index + 1}",
                                        )},
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = orange,
                                        unfocusedBorderColor = Color.Black

                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.weight(1f))
                                Spacer(modifier = Modifier.width(5.dp))
                                IconButton(
                                    onClick = { viewmodel.removeTag(index) },
                                    modifier = Modifier.background(orange.copy(alpha = 0.02f))){
                                    Icon(
                                        imageVector = Icons.Default.Cancel,
                                        contentDescription = "Remove",
                                        tint = Color.Black,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                        }
                        TextButton(onClick = { viewmodel.addTag() }) {
                            Text(
                                text = "+ Add",
                                color = orange,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )}
                        Spacer(modifier = Modifier.height(15.dp))
                        Button(onClick = {
                            if(viewmodel.isValidForm){
                                val recipeonclick = AddRecipe(
                                    name = viewmodel.name,
                                    cuisine = viewmodel.cuisine,
                                    difficulty = viewmodel.selectedDifficulty!!,
                                    image = viewmodel.image,
                                    prepTimeMinutes = viewmodel.prepTimeMinutes,
                                    cookTimeMinutes = viewmodel.cookTimeMinutes,
                                    servings = viewmodel.servings,
                                    caloriesPerServing = viewmodel.caloriesPerServing,
                                    instructions = viewmodel.instructions.toList(),
                                    ingredients = viewmodel.ingredients.toList(),
                                    tags = viewmodel.tags.toList(),
                                    mealType = viewmodel.mealType.toList()
                                )
                                viewmodel.AddRecipe(recipeonclick)
                                viewmodel.reset()
                                scope.launch {
                                    viewmodel.SuccesmsgChnage("Recipe Added Successfully")
                                    delay(2000)
                                    Toast.makeText(context, viewmodel.success_msg, Toast.LENGTH_SHORT).show()
                                }
                                onSaveClick()
                            }
                            else{
                                Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                            }

                        },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = orange,
                                contentColor = Color.White
                            )){
                            Text(text = "Save",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White)
                        }
                        }
                    }
                viewmodel.recipe!=null->{
                    Column(
                        modifier = Modifier.fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "Recipe Name",
                            color = Color.Black.copy(0.8f),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        OutlinedTextField(
                            value = viewmodel.name,
                            onValueChange = { viewmodel.nameChange(it) },
                            placeholder = {
                                Text(
                                    text = "Eg.Chicken Curry",
                                    color = Color.Gray,
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = orange,
                                unfocusedBorderColor = Color.Black),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Cuisine",
                            color = Color.Black.copy(0.8f),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        OutlinedTextField(
                            value = viewmodel.cuisine,
                            onValueChange = { viewmodel.cusineChange(it) },
                            placeholder = {
                                Text(
                                    text = "Eg.Indian",
                                    color = Color.Gray,
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = orange,
                                unfocusedBorderColor = Color.Black),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Difficulty",
                            color = Color.Black.copy(0.8f),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        DifficulyRow(
                            viewmodel.allDifficulty,
                            viewmodel.selectedDifficulty!!,
                            onDifficultySelected = { difficulty ->
                                viewmodel.DiffcultyChange(difficulty)
                            })
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Image URL",
                            color = Color.Black.copy(0.8f),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        OutlinedTextField(
                            value = viewmodel.image,
                            onValueChange = { viewmodel.imageChange(it) },
                            placeholder = {
                                Text(
                                    text = "https://",
                                    color = Color.Gray,
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = orange,
                                unfocusedBorderColor = Color.Black),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            RowSplitWithinColums(
                                "Prep Time",
                                viewmodel.prepTimeMinutes.toString(),
                                "Eg.10",
                                valueChange = { value ->
                                    viewmodel.prepTimeMinutesChange(value.toIntOrNull() ?: 0)
                                },
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            RowSplitWithinColums(
                                "Cook Time",
                                viewmodel.cookTimeMinutes.toString(),
                                "Eg.20",
                                valueChange = { value ->
                                    viewmodel.cookTimeMinutesChange(value.toIntOrNull() ?: 0)
                                },
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            RowSplitWithinColums(
                                "Servings",
                                viewmodel.servings.toString(),
                                "Eg.2",
                                valueChange = { value ->
                                    viewmodel.servingsChange(value.toIntOrNull() ?: 0)
                                },
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            RowSplitWithinColums(
                                "Calories Per Serving",
                                viewmodel.caloriesPerServing.toString(),
                                "Eg.100",
                                valueChange = { value ->
                                    viewmodel.caloriesPerServingChange(value.toIntOrNull() ?: 0)
                                },
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Instructions",
                            color = Color.Black.copy(0.8f),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        viewmodel.instructions.forEachIndexed { index, instruction ->
                            Row(modifier = Modifier.fillMaxWidth()) {
                                OutlinedTextField(
                                    value = instruction,
                                    onValueChange = { viewmodel.updateInstruction(index, it) },
                                    placeholder = {
                                        Text(
                                            text = "Instruction ${index + 1}",
                                            color = Color.Gray,
                                        )
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = orange,
                                        unfocusedBorderColor = Color.Black),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.weight(1f)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                IconButton(
                                    onClick = { viewmodel.removeInstruction(index) },
                                    modifier = Modifier.background(orange.copy(alpha = 0.02f))
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Cancel,
                                        contentDescription = "Remove",
                                        tint = Color.Black,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                        }
                        TextButton(onClick = { viewmodel.addInstruction() }) {
                            Text(
                                text = "+ Add",
                                color = orange,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Ingredients",
                            color = Color.Black.copy(0.8f),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        viewmodel.ingredients.forEachIndexed { index, ingredient ->
                            Row(modifier = Modifier.fillMaxWidth()) {
                                OutlinedTextField(
                                    value = ingredient,
                                    onValueChange = { viewmodel.updateIngredient(index, it) },
                                    placeholder = {
                                        Text(
                                            text = "Ingredient ${index + 1}",
                                        )
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = orange,
                                        unfocusedBorderColor = Color.Black),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.weight(1f))
                                Spacer(modifier = Modifier.width(5.dp))
                                IconButton(
                                    onClick = { viewmodel.removeIngredient(index) },
                                    modifier = Modifier.background(orange.copy(alpha = 0.02f))){
                                    Icon(
                                        imageVector = Icons.Default.Cancel,
                                        contentDescription = "Remove",
                                        tint = Color.Black,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                        }
                        TextButton(onClick = { viewmodel.addIngredient() }) {
                            Text(
                                text = "+ Add",
                                color = orange,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )}
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text="Meal Type",
                            color = Color.Black.copy(0.8f),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(5.dp))
                        viewmodel.mealType.forEachIndexed { index, mealType ->
                            Row(modifier = Modifier.fillMaxWidth()) {
                                OutlinedTextField(
                                    value = mealType,
                                    onValueChange = { viewmodel.updateMealType(index, it) },
                                    placeholder = {
                                        Text(
                                            text = "Meal Type ${index + 1}",
                                        )},
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = orange,
                                        unfocusedBorderColor = Color.Black),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.weight(1f))
                                Spacer(modifier = Modifier.width(5.dp))
                                IconButton(
                                    onClick = { viewmodel.removeMealType(index) },
                                    modifier = Modifier.background(orange.copy(alpha = 0.02f))){
                                    Icon(
                                        imageVector = Icons.Default.Cancel,
                                        contentDescription = "Remove",
                                        tint = Color.Black,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                        }
                        TextButton(onClick = { viewmodel.addMealType() }) {
                            Text(
                                text = "+ Add",
                                color = orange,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )}
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text="Tags",
                            color = Color.Black.copy(0.8f),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(5.dp))
                        viewmodel.tags.forEachIndexed { index, tag ->
                            Row(modifier = Modifier.fillMaxWidth()) {
                                OutlinedTextField(
                                    value = tag,
                                    onValueChange = { viewmodel.updateTag(index, it) },
                                    placeholder = {
                                        Text(
                                            text = "Tag ${index + 1}",
                                        )},
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = orange,
                                        unfocusedBorderColor = Color.Black),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.weight(1f))
                                Spacer(modifier = Modifier.width(5.dp))
                                IconButton(
                                    onClick = { viewmodel.removeTag(index) },
                                    modifier = Modifier.background(orange.copy(alpha = 0.02f))){
                                    Icon(
                                        imageVector = Icons.Default.Cancel,
                                        contentDescription = "Remove",
                                        tint = Color.Black,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                        }
                        TextButton(onClick = { viewmodel.addTag() }) {
                            Text(
                                text = "+ Add",
                                color = orange,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )}
                        Spacer(modifier = Modifier.height(15.dp))
                        Button(onClick = {
                            if(viewmodel.isValidForm){
                                val recipeedited = AddRecipe(
                                     id = viewmodel.recipe!!.id,
                                    name = viewmodel.name,
                                    cuisine = viewmodel.cuisine,
                                    difficulty = viewmodel.selectedDifficulty!!,
                                    image = viewmodel.image,
                                    prepTimeMinutes = viewmodel.prepTimeMinutes,
                                    cookTimeMinutes = viewmodel.cookTimeMinutes,
                                    servings = viewmodel.servings,
                                    caloriesPerServing = viewmodel.caloriesPerServing,
                                    instructions = viewmodel.instructions.toList(),
                                    ingredients = viewmodel.ingredients.toList(),
                                    tags = viewmodel.tags.toList(),
                                    mealType = viewmodel.mealType.toList()
                                )
                                viewmodel.updateRecipe(recipeedited)
                                viewmodel.changeRecipestatustonull()
                                viewmodel.reset()
                              scope.launch {
                                  viewmodel.SuccesmsgChnage("Recipe Updated Successfully")
                                  delay(2000)
                                  Toast.makeText(context, viewmodel.success_msg, Toast.LENGTH_SHORT).show()
                              }
                                onSaveClick()
                            }
                            else{
                                Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                            }

                        },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = orange,
                                contentColor = Color.White
                            )){
                            Text(text = "Save",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White)
                        }
                    }
                }
                }
            }
        }
    }
// we removedviewmodel.recipe!!.instructions and others things to viewmoidel.instructions bcz we copied all data of recipe to the viewmodel variables so use directly and we also removed bcz when called viewmodel add then it adds to instructions and to actual viewmodel.recipe!!.instructions