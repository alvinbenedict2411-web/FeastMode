package com.example.beastmode_2.Presentation.Viewmodels.ViewModels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.beastmode_2.Data.Remote.KtorClient
import com.example.beastmode_2.Data.Remote.RecipeApiService
import com.example.beastmode_2.Data.RoomDatabase.AddRecipe
import com.example.beastmode_2.Data.RoomDatabase.DataBase
import com.example.beastmode_2.Data.repository.RepositoryImplementation
import com.example.beastmode_2.Domain.Repository.Repository
import kotlinx.coroutines.launch

class CustomRecipeViewModel(application: Application): AndroidViewModel(application) {
    private val client = KtorClient.client
    private val apiservice = RecipeApiService(client)
    private val dao = DataBase.getInstance(application).recipeDao()
    private val repository: Repository = RepositoryImplementation(apiservice, dao)
    var isloading by mutableStateOf(false)
        private set
    var errr_msg by mutableStateOf<String?>(null)
        private set
    var recipe by mutableStateOf<List<AddRecipe>>(emptyList())
        private set
    var selectedCategory by mutableStateOf("All")
        private set
    var allcategories by mutableStateOf<List<String>>(listOf("All"))
        private set
    var getallRecipes by mutableStateOf<List<AddRecipe>>(emptyList())
        private set

    init {
        getdetails()
    }
    fun getdetails() {
        isloading = true
        errr_msg = null
        viewModelScope.launch {
            try {
                val result = repository.getAllRecipesRoom()
                getallRecipes = result
                val cusines = result.map { it.cuisine }.distinct().sorted()
                allcategories = listOf("All") + cusines
                applyfilters()
            } catch (e: Exception) {
                errr_msg = e.message ?: "Unknown Error"
            } finally {
                isloading = false
            }
        }
    }

    fun applyfilters() {
        if (selectedCategory == "All") {
            recipe = getallRecipes
        } else {
            recipe = getallRecipes.filter { it.cuisine == selectedCategory }
        }
    }

    fun OnselectedCategoryChange(category: String) {
        selectedCategory = category
        applyfilters()
    }
}