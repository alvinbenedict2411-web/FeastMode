package com.example.beastmode_2.Domain.Repository

import com.example.beastmode_2.Data.Remote.DTO.RecipeDTO
import com.example.beastmode_2.Data.RoomDatabase.AddRecipe

//This is a contract between the repository and the view model
interface Repository {
    suspend fun getallRecipies(): List<RecipeDTO>

    suspend fun getRecipethroughId(id: Int): RecipeDTO

    suspend fun AddRecipeRoom(recipe: AddRecipe)

    suspend fun getAllRecipesRoom(): List<AddRecipe>

    suspend fun getRecipeByIdRoom(id: Int): AddRecipe

    suspend fun InsertRecipe(recipe:AddRecipe)

    suspend fun UpdateRecipeRoom(recipe: AddRecipe)

    suspend fun DeleteRecipe(recipe: AddRecipe)
}