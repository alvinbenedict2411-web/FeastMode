package com.example.beastmode_2.Data.repository
import com.example.beastmode_2.Data.Remote.DTO.RecipeDTO
import com.example.beastmode_2.Data.Remote.RecipeApiService
import com.example.beastmode_2.Data.RoomDatabase.AddRecipe
import com.example.beastmode_2.Data.RoomDatabase.Dao
import com.example.beastmode_2.Domain.Repository.Repository

//This is the implementation of the repository or implementation of the contract
class RepositoryImplementation(private val apiservice: RecipeApiService,
    private val dao: Dao): Repository {

    override suspend fun getallRecipies(): List<RecipeDTO> {
        return apiservice.getallRecipes().recipes
    }

    override suspend fun getRecipethroughId(id: Int): RecipeDTO {
        return apiservice.getRecipethroughId(id)
    }

    override suspend fun AddRecipeRoom(recipe: AddRecipe) {
        dao.insertRecipe(recipe)
    }
    override suspend fun getAllRecipesRoom(): List<AddRecipe> {
        return dao.getAllRecipes()
    }

    override suspend fun getRecipeByIdRoom(id: Int): AddRecipe {
        return dao.getRecipeById(id)
    }

    override suspend fun InsertRecipe(recipe: AddRecipe) {
        dao.insertRecipe(recipe)
    }

    override suspend fun UpdateRecipeRoom(recipe: AddRecipe) {
        dao.updateRecipe(recipe)
    }

    override suspend fun DeleteRecipe(recipe: AddRecipe) {
        dao.deleteRecipe(recipe)
    }
}
