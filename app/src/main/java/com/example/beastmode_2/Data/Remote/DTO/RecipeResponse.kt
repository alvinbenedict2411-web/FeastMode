package com.example.beastmode_2.Data.Remote.DTO

import kotlinx.serialization.Serializable
//this is called as response mapping as we are mapping the response of  the server to the data class
@Serializable
data class RecipeResponse(
    val limit: Int,
    val recipes: List<RecipeDTO>,
    val skip: Int,
    val total: Int
)