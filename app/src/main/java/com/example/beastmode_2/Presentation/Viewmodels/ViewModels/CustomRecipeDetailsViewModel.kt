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

class CustomRecipeDetailsViewModel(application:Application):AndroidViewModel(application){
    var isloading by mutableStateOf(false)
        private set
    var errr_msg by mutableStateOf<String?>(null)
        private set
   private val client= KtorClient.client
    private val apiService= RecipeApiService(client)
    private val dao= DataBase.getInstance(application).recipeDao()
    var repository: Repository= RepositoryImplementation(apiService,dao)
        private set
    var recipe by mutableStateOf<AddRecipe?>(null)
        private set
    var AlertDialogVisble by mutableStateOf(false)
    private set
    fun getrecipethroughId(id: Int){
        isloading=true
        errr_msg=null
            viewModelScope.launch {
                try {
                    recipe = repository.getRecipeByIdRoom(id)
                }
                catch (e:Exception){
                    errr_msg=e.message?:"Unknown Error"
                }
                finally {
                    isloading=false
                }
            }
    }
    fun ChangeAlertDialogVisibility(state:Boolean){
        AlertDialogVisble=state
    }
    fun deleteRecipe(recipe: AddRecipe){
        viewModelScope.launch {
            repository.DeleteRecipe(recipe)
        }
    }
    }