package com.example.beastmode_2.Data.RoomDatabase
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "recipes")
data class AddRecipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val caloriesPerServing: Int,
    val cookTimeMinutes: Int,
    val cuisine: String,
    val difficulty: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val mealType: List<String>,
    val name: String,
    val prepTimeMinutes: Int,
    val servings: Int,
    val tags: List<String>,
    val image: String
)