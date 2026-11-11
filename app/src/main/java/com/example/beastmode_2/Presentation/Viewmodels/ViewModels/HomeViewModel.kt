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
import com.example.beastmode_2.Domain.Repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel(val application: Application): ViewModel() {
    private val client= KtorClient.client
    //create an object for api service response
    private val apiservice= RecipeApiService(client)
    private val dao= DataBase.getInstance(application).recipeDao()
   private val repositoryImple: Repository= RepositoryImplementation(apiservice,dao)

    //for loading screen
    var is_loading by mutableStateOf(false)
        private set
    var  error_msg by mutableStateOf<String?>(null)
        private set
    //used for selecting particular recipes based on the category
    var recipes by mutableStateOf<List<RecipeDTO>>(emptyList())
        private set
    var allcategory by mutableStateOf<List<String>>(listOf("All"))
    var selectedCategory by mutableStateOf("All")
        private set
    private var allrecipe: List<RecipeDTO> = emptyList()
    // runs automatically when the viewmodel instance is created
    init {
        getrecipe()
    }

    fun getrecipe(){
        is_loading=true
        error_msg=null
        viewModelScope.launch {
            try{
                val result=repositoryImple.getallRecipies()
                allrecipe=result
                val cusines=result.map { it.cuisine }.distinct().sorted()
                allcategory=listOf("All")+cusines
                applyfilters()
            }
            catch (e:Exception){
                error_msg=e.message?:"Unknown Error"
            }
            finally {
                is_loading=false
            }
        }
    }
    private fun applyfilters(){
        if(selectedCategory=="All"){
            recipes=allrecipe
        }
        else{
            recipes=allrecipe.filter { it.cuisine==selectedCategory}
        }
    }
    fun onselectedcategory(category: String){
        selectedCategory=category
        applyfilters()
    }
}