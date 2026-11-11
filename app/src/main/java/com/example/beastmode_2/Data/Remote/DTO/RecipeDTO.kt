package com.example.beastmode_2.Data.Remote.DTO

import kotlinx.serialization.Serializable

@Serializable
data class RecipeDTO(
    val caloriesPerServing: Int,
    val cookTimeMinutes: Int,
    val cuisine: String,
    val difficulty: String,
    val id: Int,
    val image: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val mealType: List<String>,
    val name: String,
    // @SerialName("name") val RecipeName:String,
    //If wanted a CUSTOM NAME THEN USE @SerialName
    val prepTimeMinutes: Int,
    val rating: Double,
    val reviewCount: Int,
    val servings: Int,
    val tags: List<String>,
    val userId: Int
)