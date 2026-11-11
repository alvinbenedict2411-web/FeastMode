package com.example.beastmode_2.Presentation.Viewmodels.ViewModels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beastmode_2.Data.Remote.DTO.RecipeDTO
import com.example.beastmode_2.Data.Remote.KtorClient
import com.example.beastmode_2.Data.Remote.RecipeApiService
import com.example.beastmode_2.Data.RoomDatabase.DataBase
import com.example.beastmode_2.Data.repository.RepositoryImplementation
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(application: Application): ViewModel() {
   private val client= KtorClient.client
    private val apiservice= RecipeApiService(client)
    private val dao= DataBase.getInstance(application).recipeDao()
    private val repository = RepositoryImplementation(apiservice,dao)
    // for loading screen
    var isloading by mutableStateOf(false)
        private set
    //for error message
    var error_msg by mutableStateOf<String?>(null)
        private set
    var recipe by mutableStateOf<RecipeDTO?>(null)
        private set
    fun getrecipe(id:Int){
        isloading=true
        error_msg=null
        viewModelScope.launch {
            try {
               recipe= repository.getRecipethroughId(id)
            }
            catch (e: Exception){
                error_msg=e.message?:"Unknown Error"
            }
            finally {
                isloading=false
            }
        }
    }
}