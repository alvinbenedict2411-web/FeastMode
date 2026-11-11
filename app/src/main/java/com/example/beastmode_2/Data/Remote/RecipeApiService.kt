package com.example.beastmode_2.Data.Remote

import com.example.beastmode_2.Data.Remote.DTO.AddRecipeRequest
import com.example.beastmode_2.Data.Remote.DTO.RecipeDTO
import com.example.beastmode_2.Data.Remote.DTO.RecipeResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RecipeApiService(private val client: HttpClient) {
    suspend fun getallRecipes(): RecipeResponse {
        return client.get(urlString = "${KtorClient.BASE_URL}recipes")
            .body() //.body is used to covert json to model
    }

    suspend fun getRecipethroughId(id: Int): RecipeDTO {
        return client.get(urlString = "${KtorClient.BASE_URL}recipes/${id}")
            .body() //.body is used to covert json to model
    }

    //To Request to add a new recipe to the server
    suspend fun AddRecipe(request: AddRecipeRequest) {
        client.post(urlString = "${KtorClient.BASE_URL}recipes/add"){
            contentType(ContentType.Application.Json)
            setBody(request) // to convert to json format from modal
        }
    }
}