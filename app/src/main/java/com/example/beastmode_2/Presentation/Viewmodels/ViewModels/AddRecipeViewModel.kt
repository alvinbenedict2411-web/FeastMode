package com.example.beastmode_2.Presentation.Viewmodels.ViewModels
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.beastmode_2.Data.Remote.KtorClient
import com.example.beastmode_2.Data.Remote.RecipeApiService
import com.example.beastmode_2.Data.RoomDatabase.AddRecipe
import com.example.beastmode_2.Data.RoomDatabase.DataBase
import com.example.beastmode_2.Data.repository.RepositoryImplementation
import com.example.beastmode_2.Domain.Repository.Repository
import kotlinx.coroutines.launch

class AddRecipeViewModel(application: Application): AndroidViewModel(application) {
    private val client = KtorClient.client
    private val apiService = RecipeApiService(client)
    private val dao = DataBase.getInstance(application).recipeDao()
    var isloading by mutableStateOf(false)
        private set
    var repository: Repository = RepositoryImplementation(apiService, dao)
        private set
    var error_msg by mutableStateOf<String?>(null)
        private set
    var success_msg by mutableStateOf<String?>(null)
        private set
    var recipe by mutableStateOf<AddRecipe?>(null)
        private set
    var selectedDifficulty by mutableStateOf<String?>("Easy")
        private set
    var allDifficulty = listOf("Easy", "Medium", "Hard")
        private set
    var instructions = mutableStateListOf<String>("")
        private set
    var ingredients = mutableStateListOf<String>("")
        private set
    var tags = mutableStateListOf<String>("")
        private set
    var mealType = mutableStateListOf<String>("")
        private set
    var cuisine by mutableStateOf<String>("")
        private set
    var prepTimeMinutes by mutableStateOf<Int>(0)
        private set
    var cookTimeMinutes by mutableStateOf<Int>(0)
        private set
    var servings by mutableStateOf<Int>(0)
        private set
    var caloriesPerServing by mutableStateOf<Int>(0)
        private set
    var name by mutableStateOf<String>("")
        private set
    var image by mutableStateOf<String>("")
        private set

    fun cusineChange(cusine: String) {
        this.cuisine = cusine
    }

    fun nameChange(name: String) {
        this.name = name
    }

    fun imageChange(image: String) {
        this.image = image
    }

    fun prepTimeMinutesChange(prepTimeMinutes: Int) {
        this.prepTimeMinutes = prepTimeMinutes
    }

    fun cookTimeMinutesChange(cookTimeMinutes: Int) {
        this.cookTimeMinutes = cookTimeMinutes
    }

    fun servingsChange(servings: Int) {
        this.servings = servings
    }

    fun caloriesPerServingChange(caloriesPerServing: Int) {
        this.caloriesPerServing = caloriesPerServing
    }

    fun addInstruction() {
        instructions.add("")
    }

    fun addIngredient() {
        ingredients.add("")
    }

    fun addTag() {
        tags.add("")
    }

    fun addMealType() {
        mealType.add("")
    }

    fun removeInstruction(index: Int) {
        if (instructions.size > 1) {
            instructions.removeAt(index)
        } else {
            instructions[0] = ""
        }
    }

    fun removeIngredient(index: Int) {
        if (ingredients.size > 1) {
            ingredients.removeAt(index)
        } else {
            ingredients[0] = ""
        }
    }

    fun removeTag(index: Int) {
        if (tags.size > 1) {
            tags.removeAt(index)
        } else {
            tags[0] = ""
        }
    }
    fun removeMealType(index: Int) {
        if (mealType.size > 1) {
            mealType.removeAt(index)
        } else {
            mealType[0] = ""
        }
    }
    fun updateInstruction(index: Int, instruction: String) {
        instructions[index] = instruction
    }
    fun updateIngredient(index: Int, ingredient: String) {
        ingredients[index] = ingredient
    }
    fun updateTag(index: Int, tag: String) {
        tags[index] = tag
    }
    fun updateMealType(index: Int, mealType: String) {
        this.mealType[index] = mealType
    }
    fun AddRecipe(recipe: AddRecipe) {
        viewModelScope.launch {
            try {
                repository.AddRecipeRoom(recipe)
            } catch (e: Exception) {
                error_msg = e.message ?: "Unknown Error"
            } finally {
                if (error_msg == null) {
                    success_msg = "Recipe Added Successfully"
                } else {
                    success_msg = "Error Adding Recipe"
                }
            }
        }
    }

    fun DiffcultyChange(difficulty: String) {
        selectedDifficulty = difficulty
    }

    fun EditRecipeExists(id: Int) {
        isloading = true
        error_msg = null
        viewModelScope.launch {
            try {
                recipe = repository.getRecipeByIdRoom(id)
            } catch (e: Exception) {
                error_msg = e.message ?: "Unknown Error"
            }
            finally {
                isloading = false
            }
        }
    }
    fun updateRecipe(recipe: AddRecipe) {
        viewModelScope.launch {
            try {
                repository.UpdateRecipeRoom(recipe)
            } catch (e: Exception) {
                error_msg = e.message ?: "Unknown Error"
            }
        }
    }
    var isValidForm: Boolean = false
        get() =
        name.isNotBlank() &&
        cuisine.isNotBlank() &&
        selectedDifficulty != null &&
                (image.isNotBlank() || android.webkit.URLUtil.isValidUrl(image)/*Checks if the url is valid*/) &&
        instructions.all { it.isNotBlank() } &&
                ingredients.all { it.isNotBlank() } &&
        tags.all { it.isNotBlank() } &&
        mealType.all { it.isNotBlank() }
    fun reset(){
        name=""
        cuisine=""
        image=""
      instructions.clear()
        ingredients.clear()
        tags.clear()
        mealType.clear()
        instructions.add("")
        ingredients.add("")
        tags.add("")
        mealType.add("")
        selectedDifficulty="Easy"
        prepTimeMinutes=0
        cookTimeMinutes=0
        servings=0
        caloriesPerServing=0
    }
    fun changeRecipestatustonull(){
        recipe=null
    }
    fun updatetolatestdataoneditclick(name: String,
                                     cuisine: String,
                                     difficulty: String,
                                     image: String,
                                     prepTimeMinutes: Int,
                                     cookTimeMinutes: Int,
                                     servings: Int,
                                     caloriesPerServing: Int,
                                     instructions: List<String>,
                                     ingredients: List<String>,
                                     tags: List<String>,
                                     mealType: List<String>,
                                         id: Int){
        this.name=name
        this.cuisine=cuisine
        this.selectedDifficulty=difficulty
        this.image=image
        this.prepTimeMinutes=prepTimeMinutes
        this.cookTimeMinutes=cookTimeMinutes
        this.servings=servings
        this.caloriesPerServing=caloriesPerServing
        this.instructions.clear()
        this.ingredients.clear()
        this.tags.clear()
        this.mealType.clear()
        this.instructions.addAll(instructions)
        this.ingredients.addAll(ingredients)
        this.tags.addAll(tags)
        this.mealType.addAll(mealType)
    }
    fun SuccesmsgChnage(msg: String){
        success_msg=msg
    }
}

