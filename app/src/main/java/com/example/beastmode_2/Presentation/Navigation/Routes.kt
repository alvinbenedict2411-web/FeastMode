package com.example.beastmode_2.Presentation.Navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    object Home: Routes()
    @Serializable
    data class RecipeDetails(val id:Int):Routes()
    @Serializable
    object MyRecipes:Routes()
    @Serializable
    data class CustomRecipeDetails(val id:Int):Routes()
    @Serializable
    data class AddRecipe(val id: Int?):Routes()
}